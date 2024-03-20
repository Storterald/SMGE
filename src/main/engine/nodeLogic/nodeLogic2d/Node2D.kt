package nodeLogic.nodeLogic2d

import math.Rectangle
import math.minus
import math.times
import org.joml.Vector2f
import nodeLogic.Node

open class Node2D(
    id: String = "",
    open var position: Vector2f = Vector2f(0.0f, 0.0f),
    anchorPoint: Vector2f = Vector2f(0.0f, 0.0f),
    scale: Vector2f = Vector2f(1.0f, 1.0f),
    size: Vector2f = Vector2f(0.0f)
): Node(id) {
    init {
        require(anchorPoint.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
        require(anchorPoint.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

        require(scale.x >= 0.0f) { "The x scale must be positive" }
        require(scale.y >= 0.0f) { "The y scale must be positive" }

        require(size.x >= 0.0f) { "The x size (width) must be positive" }
        require(size.y >= 0.0f) { "The y size (height) must be positive" }
    }

    open var anchorPoint = anchorPoint
        set(value) {
            require(value.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
            require(value.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

            field = value
        }

    open var size: Vector2f = size
        set(value) {
            require(value.x >= 0.0f) { "The x size (width) must be positive" }
            require(value.y >= 0.0f) { "The y size (height) must be positive" }

            field = value
        }

    open var scale = scale
        get() {
            return if (parent != null && parent is Node2D) field * (parent as Node2D).scale else field
        }
        set(value) {
            require(value.x >= 0.0f) { "The x scale must be positive" }
            require(value.y >= 0.0f) { "The y scale must be positive" }

            field = value
        }

    val scaledSize: Vector2f
        get() {
            return scale * size
        }

    val anchoredPosition: Vector2f
        get() {
            return position - (anchorPoint * scaledSize)
        }

    val absolutePosition: Rectangle
        get() {
            val absolutePos = Rectangle(anchoredPosition, scaledSize)
            return if (parent != null && parent is Node2D) (parent as Node2D).absolutePosition + absolutePos else absolutePos
        }

}