package scene

import nodes.nodes2d.Object2D
import org.joml.Vector3f

abstract class Component

class Transform(val position: Vector3f): Component()

fun aaa() {
    val a = Object2D()
}

abstract class CustomScript(val startCode: () -> Unit, val updateCode: () -> Unit): Component()