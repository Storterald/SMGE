package nodeLogic.nodeLogic2d

import math.Vec2
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class RectangleTest {

    // ### CONSTRUCTORS TEST ###
    // ------------------------_

    @Test
    fun defaultConstructor_createsRectangleWithoutThrowing() {
        assertDoesNotThrow{ Rectangle(1.0f, 3.0f, 5.0f, 4.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfX1ValueIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(-1.0f, 1.0f, 1.0f, 1.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfY1ValueIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(1.0f, -1.0f, 1.0f, 1.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfX2ValueIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(1.0f, 1.0f, -1.0f, 1.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfY2ValueIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(1.0f, 1.0f, 1.0f, -1.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfValuesAreNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(-1.0f, -1.0f, -1.0f, -1.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfX2IsLowerThanX1() {
        assertThrows<IllegalArgumentException> { Rectangle(5.0f, 1.0f, 1.0f, 1.0f) }
    }

    @Test
    fun defaultConstructor_throwsIfY2IsLowerThanY1() {
        assertThrows<IllegalArgumentException> { Rectangle(1.0f, 4.0f, 1.0f, 1.0f) }
    }

    @Test
    fun secondConstructor_createsRectangleWithoutThrowing() {
        assertDoesNotThrow { Rectangle(100.0f, 30.0f) }
    }

    @Test
    fun secondConstructor_throwsIfWidthIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(-1.0f, 12.0f) }
    }

    @Test
    fun secondConstructor_throwsIfHeightIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(134.0f, -12.0f) }
    }

    @Test
    fun thirdConstructor_createsRectangleWithoutThrowing() {
        assertDoesNotThrow { Rectangle(Vec2(20.0f, 10.0f), 100.0f, 30.0f) }
    }

    @Test
    fun thirdConstructor_createsRectangleWithCorrectValues() {
        val rect = Rectangle(Vec2(20.0f, 10.0f), 100.0f, 30.0f)
        assertEquals(rect.x1, 20.0f)
        assertEquals(rect.y1, 10.0f)
        assertEquals(rect.x2, 120.0f)
        assertEquals(rect.y2, 40.0f)
    }

    @Test
    fun thirdConstructor_throwsIfXPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(-20.0f, 10.0f), 100.0f, 30.0f) }
    }

    @Test
    fun thirdConstructor_throwsIfYPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(20.0f, -10.0f), 100.0f, 30.0f) }
    }

    @Test
    fun thirdConstructor_throwsIfPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(-20.0f, -10.0f), 100.0f, 30.0f) }
    }

    @Test
    fun thirdConstructor_throwsIfWidthIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(20.0f, 10.0f), -100.0f, 30.0f) }
    }

    @Test
    fun thirdConstructor_throwsIfHeightIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(20.0f, 10.0f), 100.0f, -30.0f) }
    }

    @Test
    fun fourthConstructor_createsRectangleWithoutThrowing() {
        assertDoesNotThrow { Rectangle(Vec2(20.0f, 10.0f), Vec2(100.0f, 30.0f)) }
    }

    @Test
    fun fourthConstructor_createsRectangleWithCorrectValues() {
        val rect = Rectangle(Vec2(20.0f, 10.0f), Vec2(100.0f, 30.0f))
        assertEquals(rect.x1, 20.0f)
        assertEquals(rect.y1, 10.0f)
        assertEquals(rect.x2, 120.0f)
        assertEquals(rect.y2, 40.0f)
    }

    @Test
    fun fourthConstructor_throwsIfXPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(-20.0f, 10.0f), Vec2(100.0f, 30.0f)) }
    }

    @Test
    fun fourthConstructor_throwsIfYPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(20.0f, -10.0f), Vec2(100.0f, 30.0f)) }
    }

    @Test
    fun fourthConstructor_throwsIfPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(-20.0f, -10.0f), Vec2(100.0f, 30.0f)) }
    }

    @Test
    fun fourthConstructor_throwsIfXSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(20.0f, 10.0f), Vec2(-100.0f, 30.0f)) }
    }

    @Test
    fun fourthConstructor_throwsIfYSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(20.0f, 10.0f), Vec2(100.0f, -30.0f)) }
    }

    @Test
    fun fourthConstructor_throwsIfSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Rectangle(Vec2(20.0f, 10.0f), Vec2(-100.0f, -30.0f)) }
    }

}