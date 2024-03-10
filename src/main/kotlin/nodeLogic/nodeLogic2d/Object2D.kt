package nodeLogic.nodeLogic2d

import math.Vec2

open class Object2D(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), size: Vec2 = Vec2(0.0f, 0.0f)): Node2D(id, position, anchorPoint, scale) {
    init {
        require(size.x >= 0.0f) { "The x size (width) must be positive" }
        require(size.y >= 0.0f) { "The y size (height) must be positive" }
    }

    open var size: Vec2 = size
        set(value) {
            require(value.x >= 0.0f && value.y >= 0.0f) { "The size must be positive" }

            field = value
        }

    var scaledSize: Vec2 = Vec2(0.0f, 0.0f)
        get() {
            field = size * scale

            return field
        }
        private set

    var anchoredPosition: Vec2 = Vec2(0.0f, 0.0f)
        get() {
            field = position - (anchorPoint * scaledSize)

            return field
        }
        private set

    var absolutePosition: Rectangle = Rectangle(0.0f, 0.0f, 0.0f, 0.0f)
        get() {
            field = Rectangle(anchoredPosition, scaledSize)

            return field
        }
        private set
}