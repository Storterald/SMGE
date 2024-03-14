package math

import org.joml.Vector3f
import kotlin.math.pow
import kotlin.math.sqrt

operator fun Vector3f.unaryMinus(): Vector3f {
    return Vector3f(-x, -y, -z)
}

operator fun Vector3f.plus(increment: Vector3f): Vector3f {
    return Vector3f(x + increment.x, y + increment.y, z + increment.z)
}

operator fun Vector3f.plus(increment: Float): Vector3f {
    return Vector3f(x + increment, y + increment, z + increment)
}

operator fun Vector3f.minus(decrement: Vector3f): Vector3f {
    return Vector3f(x - decrement.x, y - decrement.y, z - decrement.z)
}

operator fun Vector3f.minus(decrement: Float): Vector3f {
    return Vector3f(x - decrement, y - decrement, z - decrement)
}

operator fun Vector3f.times(multiplier: Vector3f): Vector3f {
    return Vector3f(x * multiplier.x, y * multiplier.y, z * multiplier.z)
}

operator fun Vector3f.times(multiplier: Float): Vector3f {
    return Vector3f(x * multiplier, y * multiplier, z * multiplier)
}

operator fun Vector3f.div(divisor: Int): Vector3f {
    return Vector3f(x / divisor, y / divisor, z / divisor)
}

fun Vector3f.length(): Float {
    return sqrt(x.pow(2) + y.pow(2) + z.pow(2))
}

fun Vector3f.normalize(): Vector3f {
    return this / length()
}

// ### PRODUCTS ###
// ----------------

fun Vector3f.dot(rhs: Vector3f): Float {
    return (x * rhs.x) + (y * rhs.y) + (z * rhs.z)
}

fun Vector3f.cross(rhs: Vector3f): Vector3f {
    val crossX = y * rhs.z - z * rhs.y
    val crossY = z * rhs.x - x * rhs.z
    val crossZ = x * rhs.y - y * rhs.x

    return Vector3f(crossX, crossY, crossZ)
}