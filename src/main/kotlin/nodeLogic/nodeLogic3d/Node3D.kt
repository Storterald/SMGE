package nodeLogic.nodeLogic3d

import nodeLogic.Node
import math.Vec3

open class Node3D(id: String = "", position: Vec3 = Vec3(0.0f, 0.0f, 0.0f), anchorPoint: Vec3 = Vec3(0.0f, 0.0f, 0.0f), scale: Vec3 = Vec3(1.0f, 1.0f, 1.0f)): Node(id) {
    init {
        require(position.x >= 0.0f) { "The x position must be positive" }
        require(position.y >= 0.0f) { "The y position must be positive" }
        require(position.z >= 0.0f) { "The y position must be positive" }

        require(anchorPoint.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
        require(anchorPoint.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
        require(anchorPoint.z in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

        require(scale.x >= 0.0f) { "The x scale must be positive" }
        require(scale.y >= 0.0f) { "The y scale must be positive" }
        require(scale.z >= 0.0f) { "The y scale must be positive" }
    }

    open var position = position
        set(value) {
            require(value.x >= 0.0f) { "The x position must be positive" }
            require(value.y >= 0.0f) { "The y position must be positive" }
            require(value.z >= 0.0f) { "The y position must be positive" }

            field = value
        }

    open var anchorPoint = anchorPoint
        set(value) {
            require(value.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
            require(value.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
            require(value.z in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

            field = value
        }

    open var scale = scale
        set(value) {
            require(value.x >= 0.0f) { "The x scale must be positive" }
            require(value.y >= 0.0f) { "The y scale must be positive" }
            require(value.z >= 0.0f) { "The y scale must be positive" }

            field = value
        }

}