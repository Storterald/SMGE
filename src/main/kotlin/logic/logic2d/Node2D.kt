package logic.logic2d

import logic.Node
import math.Vec2

open class Node2D(initialId: String = "", initialPosition: Vec2 = Vec2(0.0f, 0.0f), initialAnchorPoint: Vec2 = Vec2(0.0f, 0.0f)): Node(initialId) {
    init {
        require(initialPosition.x >= 0.0f) { "The x position must be positive" }
        require(initialPosition.y >= 0.0f) { "The y position must be positive" }

        require(initialAnchorPoint.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
        require(initialAnchorPoint.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
    }

    var position = initialPosition
        set(value) {
            require(value.x >= 0.0f) { "The x position must be positive" }
            require(value.y >= 0.0f) { "The y position must be positive" }

            field = value
        }

    var anchorPoint = initialAnchorPoint
        set(value) {
            require(value.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
            require(value.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

            field = value
        }
}