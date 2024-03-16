package nodeLogic.nodeLogic2d

import org.joml.Vector2f
import scene.Component

open class Object2D(
    id: String = "",
    position: Vector2f = Vector2f(0.0f),
    anchorPoint: Vector2f = Vector2f(0.5f),
    scale: Vector2f = Vector2f(1.0f),
    size: Vector2f = Vector2f(0.0f)
): Node2D(id, position, anchorPoint, scale, size) {
    init {
        require(size.x >= 0.0f) { "The x size (width) must be positive" }
        require(size.y >= 0.0f) { "The y size (height) must be positive" }
    }

    val components = HashMap<Class<*>, Component>()

    inline fun <reified T: Component> addComponent(component: T) {
        check(!components.containsKey(T::class.java)) { "A component of this type already exists." }
        components[T::class.java] = component
    }

    inline fun <reified T: Component> setComponent(component: T) {
        if (!components.containsKey(T::class.java)) addComponent(component)
        else components[T::class.java] = component
    }
}