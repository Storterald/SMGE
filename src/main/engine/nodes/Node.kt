package nodes

import renderEngine.Mesh
import scene.Scene
import kotlin.reflect.KClass

open class Node(id: String = "") {
    init {
        if (id != "") {
            require(id.trim() != "") { "The id must contain at least one non ' ' char." }
            require(id[0] != ' ') { "The id mustn't have a space as it's first character." }
        }
    }

    var id: String = id
        set(value) {
            require(value != "") { "The id must contain at least one char." }
            require(value.trim() != "") { "The id must contain at least one non ' ' char." }
            require(value[0] != ' ') { "The id mustn't have a space as it's first character." }

            field = value
        }

    private var children: MutableList<Node> = mutableListOf()

    var scene: Scene? = null
        private set(value) {
            field = value
            children.forEach { it.scene = value }
        }

    open var visible = true
        set(value) {
            if (parent != null && scene != null) {
                if (parent == scene || scene!!.nodesToRender[parent] == true) {
                    changeNodeAndBelowRender(value)
                }
            }

            field = value
        }

    var parent: Node? = null
        private set(value) {
            check(parent == null || value == null) { "You can't change the parent of the node to null." }
            check(value != this)
            field = value
        }

    lateinit var mesh: Mesh

    fun isMeshInitialized(): Boolean {
        return this::mesh.isInitialized
    }

    fun getType(): KClass<*> {
        return this::class
    }

    fun addChild(node: Node) {
        require(node != this) { "A node cannot have itself as a child." }
        require(node.parent == null) { "The node is already a child of another node." }
        require(node !is Scene) { "You can't add a scene as a child." }

        node.parent = this

        if (this is Scene) {
            node.scene = this
            node.loadNodeAndBelowToScene()
        } else if (scene != null) {
            node.scene = scene
            node.loadNodeAndBelowToScene()
        }

        children.add(node)

        node.loadMesh()
    }

    fun getChildById(id: String): Node {
        require(id != "") { "The id must contain at least one char." }
        require(id.trim() != "") { "The id must contain at least one non ' ' char." }
        require(id[0] != ' ') { "The id mustn't have a space as it's first character." }

        children.forEach {
            if (it.id == id) return it
        }

        throw IllegalStateException("No child has the id '$id'.")
    }

    fun getChildAtIndex(i: Int): Node {
        check(children.size > 0) { "The node does not contain any children" }
        require(i >= 0) { "The index must be 0 or higher" }
        require(i < children.size) { "The index must be between 0 and children.size-1" }

        return children[i]
    }

    fun getChildrenCount(): Int {
        return children.size
    }

    fun getChildrenOfType(type: KClass<*>): List<Node> {
        require(Node::class.java.isAssignableFrom(type.java)) { "The type must be a Node or one of its sub classes." }

        val out: MutableList<Node> = mutableListOf()
        children.forEach {
            if (it::class == type) {
                out.add(it)
            }
        }

        return out
    }

    fun removeChild(node: Node) {
        check(children.size > 0) { "The node does not contain any children" }
        check (children.remove(node)) { "The given node isn't in the children list." }

        node.parent = null

        if (this is Scene || scene != null) {
            node.unloadNodeAndBelowFromScene()
            node.scene = null
        }
    }

    fun removeChildById(id: String) {
        require(id != "") { "The id must contain at least one char." }
        require(id.trim() != "") { "The id must contain at least one non ' ' char." }
        require(id[0] != ' ') { "The id mustn't have a space as it's first character." }

        children.forEach {
            if (it.id == id) {
                removeChild(it)
                return
            }
        }
        throw IllegalStateException("No node with the given id is in the children list.")
    }

    fun removeChildAtIndex(i: Int) {
        require(i >= 0) { "The index must be 0 or higher" }
        require(i < children.size) { "The index must be higher than 0 and  lower than children.size" }

        removeChild(children[i])
    }

    fun removeAllChildren() {
        val nodesToBeRemoved: MutableList<Node> = mutableListOf()

        /* Using removeChild(it) inside the forEach causes a ConcurrentModificationException,
         * Only way to fix this is to add the nodes that need to be removed in another list and then remove them.
         */
        children.forEach { nodesToBeRemoved.add(it) }

        nodesToBeRemoved.forEach { removeChild(it) }
    }

    fun removeChildrenOfType(type: KClass<*>) {
        require(Node::class.java.isAssignableFrom(type.java)) { "The type must be a Node or one of its sub classes." }

        val nodesToBeRemoved: MutableList<Node> = mutableListOf()

        /* Using removeChild(it) inside the forEach causes a ConcurrentModificationException,
         * Only way to fix this is to add the nodes that need to be removed in another list and then remove them.
         */
        children.forEach {
            if (it::class == type) {
                nodesToBeRemoved.add(it)
            }
        }

        nodesToBeRemoved.forEach { removeChild(it) }
    }

    private fun loadNodeAndBelowToScene() {
        check(scene != null) { "The node isn't in a scene" }

        scene!!.nodesToRender[this] = true
        if (getChildrenCount() > 0) {
            children.forEach {
                it.loadNodeAndBelowToScene()
            }
        }
    }

    private fun unloadNodeAndBelowFromScene() {
        check(scene != null) { "The node isn't in a scene" }

        scene!!.nodesToRender.remove(this)
        if (getChildrenCount() > 0) {
            children.forEach {
                it.unloadNodeAndBelowFromScene()
            }
        }
    }

    private fun changeNodeAndBelowRender(value: Boolean) {
        check(scene != null) { "The node isn't in a scene" }

        scene!!.nodesToRender[this] = value
        if (getChildrenCount() > 0) {
            children.forEach {
                it.changeNodeAndBelowRender(value)
            }
        }
    }

    open fun loadMesh() {}
}
