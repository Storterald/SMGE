package nodes.nodes3d

import org.joml.Vector3f

open class Entity3D(id: String = "", position: Vector3f = Vector3f(0.0f, 0.0f, 0.0f), anchorPoint: Vector3f = Vector3f(0.0f, 0.0f, 0.0f), scale: Vector3f = Vector3f(1.0f, 1.0f, 1.0f)) : Node3D(id, position, anchorPoint, scale) {

}