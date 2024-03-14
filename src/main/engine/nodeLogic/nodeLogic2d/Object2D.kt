package nodeLogic.nodeLogic2d

import org.joml.Vector2f
import math.*
import scene.Component

open class Object2D(
    id: String = "",
    position: Vector2f = Vector2f(0.0f),
    anchorPoint: Vector2f = Vector2f(0.5f),
    scale: Vector2f = Vector2f(1.0f),
    size: Vector2f = Vector2f(0.0f)
): Node2D(id, position, anchorPoint, scale) {

    init {
        require(size.x >= 0.0f) { "The x size (width) must be positive" }
        require(size.y >= 0.0f) { "The y size (height) must be positive" }
    }

    val components = HashMap<Class<*>, Component>()

    open var size: Vector2f = size
        get() {
            return field
        }
        set(value) {
            require(value.x >= 0.0f) { "The x size (width) must be positive" }
            require(value.y >= 0.0f) { "The y size (height) must be positive" }

            field = value
        }

    var scaledSize: Vector2f = Vector2f(0.0f)
        get() {
            field = scale * size

            return field
        }
        private set

    var anchoredPosition: Vector2f = Vector2f(0.0f)
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

    inline fun <reified T: Component> addComponent(component: T) {
        check(!components.containsKey(T::class.java)) { "A component of this type already exists." }
        components[T::class.java] = component
    }

    inline fun <reified T: Component> setComponent(component: T) {
        if (!components.containsKey(T::class.java)) addComponent(component)
        else components[T::class.java] = component
    }
}