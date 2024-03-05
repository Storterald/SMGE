package logic

import math.Vec3

class GenericNode3d(iId: String = "", iPosition: Vec3 = Vec3(0.0f, 0.0f, 0.0f), iAnchorPoint: Vec3 = Vec3(0.5f, 0.5f, 0.5f)) {
    init {
        if (iId != "") {
            require(iId.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
            require(iId[0] != ' ') { "The id mustn't have a space as it's first character." }
        }

        require(iPosition.x >= 0.0f) { "The x position must be positive" }
        require(iPosition.y >= 0.0f) { "The y position must be positive" }
        require(iPosition.z >= 0.0f) { "The y position must be positive" }


        require(iAnchorPoint.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
        require(iAnchorPoint.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
        require(iAnchorPoint.z in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
    }

    var id: String = iId
        set(value) {
            require(value != "") { "The id must contain at least one char." }
            require(value.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
            require(value[0] != ' ') { "The id mustn't have a space as it's first character." }

            field = value
        }

    var position = iPosition
        set(value) {
            require(value.x >= 0.0f) { "The x position must be positive" }
            require(value.y >= 0.0f) { "The y position must be positive" }
            require(value.z >= 0.0f) { "The y position must be positive" }

            field = value
        }

    var anchorPoint = iAnchorPoint
        set(value) {
            require(value.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
            require(value.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
            require(value.z in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

            field = value
        }

    var children: MutableList<GenericNode3d> = mutableListOf()
        private set

    fun addChild(node: GenericNode3d) {
        require(node != this) { "A node cannot have itself as a child." }
        children.add(node)
    }

    fun getChildById(id: String): GenericNode3d {
        require(id != "") { "The id must contain at least one char." }
        require(id.indexOfFirst { it in " " } == -1) { "The id must contain at least one non ' ' char." }
        require(id[0] != ' ') { "The id mustn't have a space as it's first character." }

        children.forEach {
            if (it.id == id) return it
        }

        throw Exception("No child the id '$id'")
    }

    fun getChildAtIndex(i: Int): GenericNode3d {
        check(children.size > 0) { "The node does not contain any children" }
        require(i >= 0) { "The index must be 0 or higher" }
        require(i < children.size) { "The index must be between 0 and children.size-1" }

        return children[i]
    }

    fun getChildrenCount(): Int = children.size

    fun removeChild(node: GenericNode3d) {
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