package nodeLogic.nodeLogic2d

import nodeLogic.Node
import math.Vec2

open class Node2D(id: String = "", open var position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f)): Node(id) {
    init {
        require(anchorPoint.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
        require(anchorPoint.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

        require(scale.x >= 0.0f) { "The x scale must be positive" }
        require(scale.y >= 0.0f) { "The y scale must be positive" }
    }

    open var anchorPoint = anchorPoint
        set(value) {
            require(value.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
            require(value.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

            field = value
        }

    open var scale = scale
        set(value) {
            require(value.x >= 0.0f) { "The x scale must be positive" }
            require(value.y >= 0.0f) { "The y scale must be positive" }

            field = value
        }


}