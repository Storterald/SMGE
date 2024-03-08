package nodeLogic.nodeLogic2d

import math.Vec2

data class Rectangle(val x1: Float, val y1: Float, val x2: Float, val y2: Float) {
    init {
        require(x1 >= 0.0f) { "x1 must be positive." }
        require(y1 >= 0.0f) { "y1 must be positive." }
        require(x2 >= 0.0f) { "x2 must be positive." }
        require(y2 >= 0.0f) { "y2 must be positive." }
    }

    constructor(width: Float, height: Float): this(0.0f, 0.0f, width, height) {
        require(width >= 0.0f) { "The width must be a positive number." }
        require(height >= 0.0f) { "The height must be a positive number" }
    }

    constructor(position: Vec2, width: Float, height: Float): this(position.x, position.y, width + position.x, height + position.y) {
        require(width >= 0.0f) { "The width must be a positive number." }
        require(height >= 0.0f) { "The height must be a positive number" }
    }
}
