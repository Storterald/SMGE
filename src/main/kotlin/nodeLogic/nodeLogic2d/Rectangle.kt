package nodeLogic.nodeLogic2d

import math.Vec2

data class Rectangle(val x1: Float, val y1: Float, val x2: Float, val y2: Float) {
    init {
        require(x2 >= x1) { "x2 must be higher or equal to x1" }
        require(y2 >= y1) { "y2 must be higher or equal to y1" }
    }

    constructor(width: Float, height: Float): this(0.0f, 0.0f, width, height) {
        require(width >= 0.0f) { "The width must be a positive number." }
        require(height >= 0.0f) { "The height must be a positive number" }
    }

    constructor(position: Vec2, width: Float, height: Float): this(position.x, position.y, width + position.x, height + position.y) {
        require(width >= 0.0f) { "The width must be a positive number." }
        require(height >= 0.0f) { "The height must be a positive number" }
    }

    constructor(position: Vec2, size: Vec2): this(position.x, position.y, size.x + position.x, size.y + position.y) {
        require(size.x >= 0.0f) { "The x size (width) must be a positive number" }
        require(size.y >= 0.0f) { "The y size (height) must be a positive number" }
    }
}
