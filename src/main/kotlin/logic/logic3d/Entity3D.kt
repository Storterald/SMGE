package logic.logic3d

import logic.Entity
import math.Vec3

class Entity3D(initialId: String = "", initialPosition: Vec3 = Vec3(0.0f, 0.0f, 0.0f), initialAnchorPoint: Vec3 = Vec3(0.0f, 0.0f, 0.0f)) : Entity(initialId) {
    init {
        require(initialPosition.x >= 0.0f) { "The x position must be positive" }
        require(initialPosition.y >= 0.0f) { "The y position must be positive" }
        require(initialPosition.z >= 0.0f) { "The y position must be positive" }

        require(initialAnchorPoint.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
        require(initialAnchorPoint.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
        require(initialAnchorPoint.z in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
    }

    var position = initialPosition
        set(value) {
            require(value.x >= 0.0f) { "The x position must be positive" }
            require(value.y >= 0.0f) { "The y position must be positive" }
            require(value.z >= 0.0f) { "The y position must be positive" }

            field = value
        }

    var anchorPoint = initialAnchorPoint
        set(value) {
            require(value.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
            require(value.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
            require(value.z in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

            field = value
        }

}