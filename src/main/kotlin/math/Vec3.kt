package math

import kotlin.math.pow
import kotlin.math.sqrt

data class Vec3(var x: Float, var y: Float, var z: Float) {

    // ### OPERATORS ###
    // -----------------

    operator fun unaryMinus() = Vec3(-x, -y, -z)

    operator fun plus(increment: Vec3): Vec3 {
        return Vec3(x + increment.x, y + increment.y, z + increment.z)
    }

    operator fun plus(increment: Float): Vec3 {
        return Vec3(x + increment, y + increment, z + increment)
    }

    operator fun minus(decrement: Vec3): Vec3 {
        return Vec3(x - decrement.x, y - decrement.y, z - decrement.z)
    }

    operator fun minus(decrement: Float): Vec3 {
        return Vec3(x - decrement, y - decrement, z - decrement)
    }

    operator fun times(multiplier: Vec3): Vec3 {
        return Vec3(x * multiplier.x, y * multiplier.y, z * multiplier.z)
    }

    operator fun times(multiplier: Float): Vec3 {
        return Vec3(x * multiplier, y * multiplier, z * multiplier)
    }

    operator fun div(divisor: Vec3): Vec3 {
        return Vec3(x / divisor.x, y / divisor.y, z / divisor.z)
    }

    operator fun div(divisor: Float): Vec3 {
        return Vec3(x / divisor, y / divisor, z / divisor)
    }

    fun length(): Float {
        return sqrt(x.pow(2) + y.pow(2) + z.pow(2))
    }

    fun normalize(): Vec3 {
        return this / length()
    }

    // ### PRODUCTS ###
    // ----------------

    fun dot(rhs: Vec3): Float {
        return (x * rhs.x) + (y * rhs.y) + (z * rhs.z)
    }

    fun cross(rhs: Vec3): Vec3 {
        val crossX = y * rhs.z - z * rhs.y
        val crossY = z * rhs.x - x * rhs.z
        val crossZ = x * rhs.y - y * rhs.x

        return Vec3(crossX, crossY, crossZ)
    }
 }

