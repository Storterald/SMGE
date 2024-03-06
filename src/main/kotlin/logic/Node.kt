package logic

open class Node(initialId: String = "") {
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

    var parent: Node? = null
        private set(value) {
            check(parent == null || value == null) { "You can't change the parent of the node." }
            check(value != this)
            field = value
        }

    fun addChild(node: Node) {
        require(node != this) { "A node cannot have itself as a child." }
        node.parent = this
        children.add(node)
    }

    fun getChildById(id: String): Node {
        require(id != "") { "The id must contain at least one char." }
        require(id.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
        require(id[0] != ' ') { "The id mustn't have a space as it's first character." }

        children.forEach {
            if (it.id == id) return it
        }

        throw Exception("No child the id '$id'")
    }

    fun getChildAtIndex(i: Int): Node {
        check(children.size > 0) { "The node does not contain any children" }
        require(i >= 0) { "The index must be 0 or higher" }
        require(i < children.size) { "The index must be between 0 and children.size-1" }

        return children[i]
    }

    fun getChildrenCount(): Int = children.size

    fun removeChild(node: Node) {
        if (!children.removeAll { it == node }) throw Exception("The given node isn't in the children list.")
        node.parent = null
    }

    fun removeChildById(id: String) {
        require(id != "") { "The id must contain at least one char." }
        require(id.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
        require(id[0] != ' ') { "The id mustn't have a space as it's first character." }

        children.forEach {
            if (it.id == id) {
                children.remove(it)
                it.parent = null
                return
            }
        }
        throw Exception("No node with the given id is in the children list.")
    }

    fun removeChildAtIndex(i: Int) {
        check(children.size > 0) { "The node does not contain any children" }
        require(i >= 0) { "The index must be 0 or higher" }
        require(i < children.size) { "The index must be between 0 and children.size-1" }

        children[i].parent = null
        children.removeAt(i)
    }

    fun removeAllChildren() {
        children.forEach {
            it.parent = null
        }
        children.clear()
    }
}
