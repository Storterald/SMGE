package nodeLogic.nodeLogic3d

import math.Vec3

open class Object3D(id: String = "", position: Vec3 = Vec3(0.0f, 0.0f, 0.0f), anchorPoint: Vec3 = Vec3(0.0f, 0.0f, 0.0f), scale: Vec3 = Vec3(1.0f, 1.0f, 1.0f), size: Vec3 = Vec3(1.0f, 1.0f, 1.0f)) : Node3D(id, position, anchorPoint, scale) {
    init {
        require(size.x >= 0.0f) { "The x size (width) must be positive" }
        require(size.y >= 0.0f) { "The y size (height) must be positive" }
        require(size.z >= 0.0f) { "The z size (depth) must be positive" }
    }

    open var size: Vec3 = size
        set(value) {
            require(value.x >= 0.0f) { "The x size (width) must be positive" }
            require(value.y >= 0.0f) { "The y size (height) must be positive" }
            require(value.z >= 0.0f) { "The z size (depth) must be positive" }

            field = value
        }

    var scaledSize: Vec3 = Vec3(0.0f, 0.0f, 0.0f)
        get() {
            field = size * scale

            return field
        }
        private set

    var anchoredPosition: Vec3 = Vec3(0.0f, 0.0f, 0.0f)
        get() {
            field = position - (anchorPoint * scaledSize)

            return field
        }
        private set
}