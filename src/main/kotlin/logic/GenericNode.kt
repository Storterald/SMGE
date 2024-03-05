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


}