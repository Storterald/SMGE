package nodeLogic.nodeLogic3d

import nodeLogic.Node
import org.joml.Vector3f

open class Node3D(
    id: String = "",
    open var position: Vector3f = Vector3f(0.0f),
    anchorPoint: Vector3f = Vector3f(0.0f),
    scale: Vector3f = Vector3f(1.0f)
): Node(id) {
    init {
        require(anchorPoint.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
        require(anchorPoint.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }
        require(anchorPoint.z in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

        require(scale.x >= 0.0f) { "The x scale must be positive" }
        require(scale.y >= 0.0f) { "The y scale must be positive" }
        require(scale.z >= 0.0f) { "The y scale must be positive" }
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