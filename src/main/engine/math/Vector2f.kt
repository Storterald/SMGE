package math

import org.joml.Vector2f
import org.joml.Vector2i
import kotlin.math.pow
import kotlin.math.sqrt

// ### OPERATORS ###
// -----------------

operator fun Vector2f.unaryMinus(): Vector2f {
    return Vector2f(-x, -y)
}

operator fun Vector2f.plus(increment: Vector2f): Vector2f {
    return Vector2f(x + increment.x, y + increment.y)
}

operator fun Vector2f.plus(increment: Float): Vector2f {
    return Vector2f(x + increment, y + increment)
}

operator fun Vector2f.minus(decrement: Vector2f): Vector2f {
    return Vector2f(x - decrement.x, y - decrement.y)
}

operator fun Vector2f.minus(decrement: Float): Vector2f {
    return Vector2f(x - decrement, y - decrement)
}

operator fun Vector2f.times(multiplier: Vector2f): Vector2f {
    return Vector2f(x * multiplier.x, y * multiplier.y)
}

operator fun Vector2f.times(multiplier: Float): Vector2f {
    return Vector2f(x * multiplier, y * multiplier)
}

operator fun Vector2f.div(divisor: Int): Vector2f {
    return Vector2f(x / divisor, y / divisor)
}

fun Vector2f.toVector2i(): Vector2i {
    return Vector2i(x.toInt(), y.toInt())
}

fun Vector2f.length(): Float {
    return sqrt(x.pow(2) + y.pow(2))
}

fun Vector2f.normalize(): Vector2f {
    return this / length()
}

// ### PRODUCTS ###
// ----------------

fun Vector2f.dot(rhs: Vector2f): Float {
    return (x * rhs.x) + (y * rhs.y)
}