package nodeLogic

abstract class Node(initialId: String = "") {
    init {
        if (initialId != "") {
            require(initialId.trim() != "") { "The id must contain at least one non ' ' char." }
            require(initialId[0] != ' ') { "The id mustn't have a space as it's first character." }
        }
    }

    var id: String = initialId
        set(value) {
            require(value != "") { "The id must contain at least one char." }
            require(value.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
            require(value[0] != ' ') { "The id mustn't have a space as it's first character." }

            field = value
        }

    var children: MutableList<Node> = mutableListOf()
        private set

    open var scene: Scene? = null

    open var visible = true
        set(value) {
            if (scene != null && this in scene!!.r) {
                scene!!.r[this] = value
                children.forEach {
                    it.visible = value
                }
            }
            field = value
        }

    var parent: Node? = null
        private set(value) {
            check(parent == null || value == null) { "You can't change the parent of the node." }
            check(value != this)
            field = value
        }

    open fun addChild(node: Node) {
        require(node != this) { "A node cannot have itself as a child." }
        require(node.parent == null) { "The node is already a child of another node." }

        node.parent = this

        if (scene != null && scene!!.r[this]!!) {
            node.scene = scene
            node.loadNodeAndBelowToScene()
        }

        children.add(node)
    }

    fun getChildById(id: String): Node {
        require(id != "") { "The id must contain at least one char." }
        require(id.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
        require(id[0] != ' ') { "The id mustn't have a space as it's first character." }

        children.forEach {
            if (it.id == id) return it
        }

        throw Exception("No child has the id '$id'.")
    }

    fun getChildAtIndex(i: Int): Node {
        check(children.size > 0) { "The node does not contain any children" }
        require(i >= 0) { "The index must be 0 or higher" }
        require(i < children.size) { "The index must be between 0 and children.size-1" }

        return children[i]
    }

    fun getChildrenCount(): Int = children.size

    open fun removeChild(node: Node) {
        if (!children.remove(node)) throw Exception("The given node isn't in the children list.")

        node.parent = null

        if (node.scene != null && node.scene!!.r[this]!!) {
            node.unloadNodeAndBelowFromScene()
        }
    }

    open fun removeChildById(id: String) {
        require(id != "") { "The id must contain at least one char." }
        require(id.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
        require(id[0] != ' ') { "The id mustn't have a space as it's first character." }

        children.forEach {
            if (it.id == id) {
                it.parent = null
                if (it.scene != null && it.scene!!.r[this]!!) {
                    it.unloadNodeAndBelowFromScene()
                }

                children.remove(it)

                return
            }
        }
        throw Exception("No node with the given id is in the children list.")
    }

    open fun removeChildAtIndex(i: Int) {
        check(children.size > 0) { "The node does not contain any children" }
        require(i >= 0) { "The index must be 0 or higher" }
        require(i < children.size) { "The index must be between 0 and children.size-1" }

        children[i].parent = null

        if (children[i].scene != null && children[i].scene!!.r[this]!!) {
            children[i].unloadNodeAndBelowFromScene()
        }

        children.removeAt(i)
    }

    open fun removeAllChildren() {
        children.forEach {
            it.parent = null

            if (it.scene != null && it.scene!!.r[this]!!) {
                it.unloadNodeAndBelowFromScene()
            }
        }
        children.clear()
    }

    fun loadNodeAndBelowToScene() {
        check(scene != null) { "The node isn't in a scene" }

        scene!!.r[this] = true
        if (getChildrenCount() > 0) {
            children.forEach {
                it.scene = scene
                it.loadNodeAndBelowToScene()
            }
        }
    }

    fun unloadNodeAndBelowFromScene() {
        check(scene != null) { "The node isn't in a scene" }
        
        if (parent == scene) parent = null
        scene!!.r.remove(this)
        scene = null
        if (getChildrenCount() > 0) {
            children.forEach {
                it.unloadNodeAndBelowFromScene()
                it.scene = null
            }
        }
    }
}
