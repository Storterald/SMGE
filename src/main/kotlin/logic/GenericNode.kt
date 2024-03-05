package logic

class GenericNode(iId: String = "", iPosition: FloatArray = floatArrayOf(0.0f, 0.0f), iAnchorPoint: FloatArray = floatArrayOf(0.5f, 0.5f)) {
    init {
        if (iId != "") {
            require(iId.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
            require(iId[0] != ' ') { "The id mustn't have a space as it's first character." }
        }

        require(iPosition.size == 2) { "The position must be a 2-dimensional vector of 2 floats." }
        require(iPosition[0] >= 0.0f) { "The x position must be positive" }
        require(iPosition[1] >= 0.0f) { "The y position must be positive" }

        require(iAnchorPoint.size == 2) { "The anchor point must be a 2-dimensional vector of 2 floats." }
        require(iAnchorPoint[0] in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
        require(iAnchorPoint[1] in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
    }

    var id: String = iId
        set(value) {
            require(value != "") { "The id must contain at least one char." }
            require(value.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
            require(value[0] != ' ') { "The id mustn't have a space as it's first character." }

            field = value
        }

    var position: FloatArray = iPosition
        set(value) {
            require(value.size == 2) { "The position must be a 2-dimensional vector of 2 floats." }
            require(value[0] >= 0.0f) { "The x position must be positive" }
            require(value[1] >= 0.0f) { "The y position must be positive" }

            field = value
        }

    var anchorPoint: FloatArray = iAnchorPoint
        set(value) {
            require(value.size == 2) { "The anchor point must be a 2-dimensional vector of 2 floats." }
            require(value[0] in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
            require(value[1] in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

            field = value
        }

    var children: MutableList<GenericNode> = mutableListOf()
        private set

    fun addChild(node: GenericNode) {
        require(node != this) { "A node cannot have itself as a child." }
        children.add(node)
    }

    fun getChildById(id: String): GenericNode {
        require(id != "") { "The id must contain at least one char." }
        require(id.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
        require(id[0] != ' ') { "The id mustn't have a space as it's first character." }

        children.forEach {
            if (it.id == id) return it
        }

        throw Exception("No child the id '$id'")
    }

    fun getChildAtIndex(i: Int): GenericNode {
        check(children.size > 0) { "The node does not contain any children" }
        require(i >= 0) { "The index must be 0 or higher" }
        require(i < children.size) { "The index must be between 0 and children.size-1" }

        return children[i]
    }

    fun getChildrenCount(): Int = children.size

    fun removeChild(node: GenericNode) {
        if (!children.removeAll { it == node }) throw Exception("The given node isn't in the children list.")
    }

    fun removeChildById(id: String) {
        require(id != "") { "The id must contain at least one char." }
        require(id.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
        require(id[0] != ' ') { "The id mustn't have a space as it's first character." }

        if (!children.removeAll { it.id == id }) throw Exception("No node with the given id is in the children list.")
    }

    fun removeChildAtIndex(i: Int) {
        check(children.size > 0) { "The node does not contain any children" }
        require(i >= 0) { "The index must be 0 or higher" }
        require(i < children.size) { "The index must be between 0 and children.size-1" }

        children.removeAt(i)
    }


}