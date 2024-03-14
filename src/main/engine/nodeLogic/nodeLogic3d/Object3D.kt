package nodeLogic.nodeLogic3d

import org.joml.Vector3f
import math.*
import scene.Component

open class Object3D(
    id: String = "",
    position: Vector3f = Vector3f(0.0f, 0.0f, 0.0f),
    anchorPoint: Vector3f = Vector3f(0.0f, 0.0f, 0.0f),
    scale: Vector3f = Vector3f(1.0f, 1.0f, 1.0f),
    size: Vector3f = Vector3f(1.0f, 1.0f, 1.0f))
: Node3D(id, position, anchorPoint, scale) {

    init {
        require(size.x >= 0.0f) { "The x size (width) must be positive" }
        require(size.y >= 0.0f) { "The y size (height) must be positive" }
        require(size.z >= 0.0f) { "The z size (depth) must be positive" }
    }

    val components = HashMap<Class<*>, Component>()

    open var size: Vector3f = size
        set(value) {
            require(value.x >= 0.0f) { "The x size (width) must be positive" }
            require(value.y >= 0.0f) { "The y size (height) must be positive" }
            require(value.z >= 0.0f) { "The z size (depth) must be positive" }

            field = value
        }

    var scaledSize: Vector3f = Vector3f(0.0f, 0.0f, 0.0f)
        get() {
            field = size * scale

            return field
        }
        private set

    var anchoredPosition: Vector3f = Vector3f(0.0f, 0.0f, 0.0f)
        get() {
            field = position - (anchorPoint * scaledSize)

            return field
        }
        private set

    inline fun <reified T: Component> addComponent(component: T) {
        check(!components.containsKey(T::class.java)) { "A component of this type already exists." }
        components[T::class.java] = component
    }

    inline fun <reified T: Component> setComponent(component: T) {
        if (!components.containsKey(T::class.java)) addComponent(component)
        else components[T::class.java] = component
    }
}