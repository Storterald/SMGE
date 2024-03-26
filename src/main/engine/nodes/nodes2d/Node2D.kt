package nodes.nodes2d

import math.Rectangle
import math.minus
import math.plus
import math.times
import org.joml.Vector2f
import nodes.Node

private var rScale = Vector2f(1.0f)

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
        set(value) {
            require(value.x >= 0.0f) { "The x scale must be positive" }
            require(value.y >= 0.0f) { "The y scale must be positive" }

            field = value
        }

    val scaledSize: Vector2f
        get() {
            rScale = Vector2f(1.0f)
            return getScaleRecursive() * size
        }

    val anchoredPosition: Vector2f
        get() {
            val ap = position - (anchorPoint * scaledSize)
            return if (parent != null && parent is Node2D) ap + (parent as Node2D).anchoredPosition else ap
        }

    val absolutePosition: Rectangle
        get() {
            return Rectangle(anchoredPosition, scaledSize)
        }

    private fun getScaleRecursive(): Vector2f {
        rScale *= scale
        if (parent != null && parent is Node2D) {
            (parent as Node2D).getScaleRecursive()
        }

        return rScale
    }

}