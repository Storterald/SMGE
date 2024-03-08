package math

import kotlin.math.pow
import kotlin.math.sqrt

data class Vec2(var x: Float, var y: Float) {

    // ### OPERATORS ###
    // -----------------

    operator fun unaryMinus() = Vec2(-x, -y)

    operator fun plus(increment: Vec2): Vec2 {
        return Vec2(x + increment.x, y + increment.y)
    }

    operator fun plus(increment: Float): Vec2 {
        return Vec2(x + increment, y + increment)
    }

    operator fun minus(decrement: Vec2): Vec2 {
        return Vec2(x - decrement.x, y - decrement.y)
    }

    operator fun minus(decrement: Float): Vec2 {
        return Vec2(x - decrement, y - decrement)
    }

    operator fun times(multiplier: Vec2): Vec2 {
        return Vec2(x * multiplier.x, y * multiplier.y)
    }

    operator fun times(multiplier: Float): Vec2 {
        return Vec2(x * multiplier, y * multiplier)
    }

    operator fun div(divisor: Vec2): Vec2 {
        return Vec2(x / divisor.x, y / divisor.y)
    }

    operator fun div(divisor: Float): Vec2 {
        return Vec2(x / divisor, y / divisor)
    }

    fun length(): Float {
        return sqrt(x.pow(2) + y.pow(2))
    }

    fun normalize(): Vec2 {
        return this / length()
    }

    // ### PRODUCTS ###
    // ----------------

    fun dot(rhs: Vec2): Float {
        return (x * rhs.x) + (y * rhs.y)
    }
}

