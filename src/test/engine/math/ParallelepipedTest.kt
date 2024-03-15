package math

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.joml.Vector3f

class ParallelepipedTest {

    // ### CONSTRUCTORS TEST ###
    // ------------------------_

    @Test
    fun defaultConstructor_createsParallelepipedWithoutThrowing() {
        assertDoesNotThrow{ Parallelepiped(1.0f, 3.0f, 2.0f, 4.0f, 5.0f, 7.0f) }
    }

    @Test
    fun defaultConstructor_doesNotThrowIfValuesAreNegativeButSizeIsPositive() {
        assertDoesNotThrow{ Parallelepiped(-1.0f, 3.0f, -7.0f, 4.0f, 5.0f, -1.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfX2IsLowerThanX1() {
        assertThrows<IllegalArgumentException> { Parallelepiped(1.0f, 3.0f, 2.0f, 0.0f, 5.0f, 7.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfY2IsLowerThanY1() {
        assertThrows<IllegalArgumentException> { Parallelepiped(1.0f, 3.0f, 2.0f, 4.0f, 2.0f, 7.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfZ2IsLowerThanZ1() {
        assertThrows<IllegalArgumentException> { Parallelepiped(1.0f, 3.0f, 2.0f, 4.0f, 4.0f, -7.0f) }
    }

    @Test
    fun secondConstructor_createsParallelepipedWithoutThrowing() {
        assertDoesNotThrow { Parallelepiped(100.0f, 30.0f, 10.0f) }
    }

    @Test
    fun secondConstructor_throwsIfWidthIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(-1.0f, 12.0f, 5.0f) }
    }

    @Test
    fun secondConstructor_throwsIfHeightIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(134.0f, -12.0f, 5.0f) }
    }

    @Test
    fun secondConstructor_throwsIfDepthIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(134.0f, 12.0f, -5.0f) }
    }

    @Test
    fun thirdConstructor_createsParallelepipedWithoutThrowing() {
        assertDoesNotThrow { Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), 100.0f, 30.0f, 30.0f) }
    }

    @Test
    fun thirdConstructor_doesNotThrowIfValuesAreNegativeButSizeIsPositive() {
        assertDoesNotThrow { Parallelepiped(Vector3f(-20.0f, -1.0f, -10.0f), 100.0f, 30.0f, 30.0f) }
    }

    @Test
    fun thirdConstructor_createsParallelepipedWithCorrectValues() {
        val rect = Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), 100.0f, 30.0f, 50.0f)
        assertEquals(rect.x1, 20.0f)
        assertEquals(rect.y1, 10.0f)
        assertEquals(rect.z1, 10.0f)
        assertEquals(rect.x2, 120.0f)
        assertEquals(rect.y2, 40.0f)
        assertEquals(rect.z2, 60.0f)
    }

    @Test
    fun thirdConstructor_throwsIfWidthIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), -100.0f, 30.0f, 3.0f) }
    }

    @Test
    fun thirdConstructor_throwsIfHeightIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), 100.0f, -30.0f, 10.0f) }
    }

    @Test
    fun thirdConstructor_throwsIfDepthIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), 100.0f, 30.0f, -10.0f) }
    }

    @Test
    fun fourthConstructor_createsParallelepipedWithoutThrowing() {
        assertDoesNotThrow { Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), Vector3f(20.0f, 10.0f, 10.0f)) }
    }

    @Test
    fun fourthConstructor_doesNotThrowIfValuesAreNegativeButSizeIsPositive() {
        assertDoesNotThrow { Parallelepiped(Vector3f(-20.0f, -10.0f, -10.0f), Vector3f(20.0f, 10.0f, 10.0f)) }
    }

    @Test
    fun fourthConstructor_createsParallelepipedWithCorrectValues() {
        val rect = Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), Vector3f(100.0f, 30.0f, 1.0f))
        assertEquals(rect.x1, 20.0f)
        assertEquals(rect.y1, 10.0f)
        assertEquals(rect.z1, 10.0f)
        assertEquals(rect.x2, 120.0f)
        assertEquals(rect.y2, 40.0f)
        assertEquals(rect.z2, 11.0f)
    }

    @Test
    fun fourthConstructor_throwsIfXSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), Vector3f(-100.0f, 30.0f, 5.0f)) }
    }

    @Test
    fun fourthConstructor_throwsIfYSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), Vector3f(100.0f, -30.0f, 1.0f)) }
    }

    @Test
    fun fourthConstructor_throwsIfZSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), Vector3f(100.0f, 30.0f, -1.0f)) }
    }

    @Test
    fun fourthConstructor_throwsIfSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Parallelepiped(Vector3f(20.0f, 10.0f, 10.0f), Vector3f(-100.0f, -30.0f, -10.0f)) }
    }
    
}