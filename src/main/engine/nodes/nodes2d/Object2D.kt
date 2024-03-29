package nodes.nodes2d

import org.joml.Vector2f
import scene.Component

open class Object2D(
    id: String = "",
    position: Vector2f = Vector2f(0.0f),
    anchorPoint: Vector2f = Vector2f(0.5f),
    scale: Vector2f = Vector2f(1.0f),
    size: Vector2f = Vector2f(0.0f),
    rotation: Vector2f = Vector2f(0.0f)
): Node2D(id, position, anchorPoint, scale, size) {
    val components = HashMap<Class<*>, Component>()

    inline fun <reified T: Component> addComponent(component: T) {
        check(!components.containsKey(T::class.java)) { "A component of this type already exists." }
        components[T::class.java] = component
    }

    inline fun <reified T: Component> setComponent(component: T) {
        if (!components.containsKey(T::class.java)) addComponent(component)
        else components[T::class.java] = component
    }

    inline fun <reified T: Component> getComponent(): T {
        check(components.containsKey(T::class.java)) { "There was no specified component \"${T::class.java}\"in the object." }
        return components[T::class.java] as T
    }
}