package objectNodeLogic.objectNodeLogic2d

import math.Vec2
import nodeLogic.nodeLogic2d.Object2D
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Object2DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowIfiPositionIsValid() {
        assertDoesNotThrow { Object2D(initialPosition = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Object2D(initialPosition = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Object2D(initialPosition = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Object2D(initialPosition = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_doesNotThrowIfiAnchorPointIsValid() {
        assertDoesNotThrow { Object2D(initialPosition = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Object2D(initialAnchorPoint = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Object2D(initialAnchorPoint = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Object2D(initialAnchorPoint = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Object2D(initialAnchorPoint = Vec2(1.1f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Object2D(initialAnchorPoint = Vec2(0.0f, 1.1f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Object2D(initialAnchorPoint = Vec2(1.1f, 1.1f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun position_doesNotThrowIfValid() {
        val objectNode = Object2D()
        assertDoesNotThrow { objectNode.position = Vec2(1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfXIsNegative() {
        val objectNode = Object2D()
        assertThrows<IllegalArgumentException> { objectNode.position = Vec2(-1.0f, 1.0f) }
    }

    @Test
    fun position_throwsIfYIsNegative() {
        val objectNode = Object2D()
        assertThrows<IllegalArgumentException> { objectNode.position = Vec2(0.3f, -1.0f) }
    }

    @Test
    fun position_throwsIfNegative() {
        val objectNode = Object2D()
        assertThrows<IllegalArgumentException> { objectNode.position = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_doesNotThrowIfValid() {
        val objectNode = Object2D()
        assertDoesNotThrow { objectNode.anchorPoint = Vec2(1.0f, 0.5f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsNegative() {
        val objectNode = Object2D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec2(-1.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsNegative() {
        val objectNode = Object2D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec2(0.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfNegative() {
        val objectNode = Object2D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsHigherThanOne() {
        val objectNode = Object2D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec2(1.1f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsHigherThanOne() {
        val objectNode = Object2D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec2(0.0f, 1.1f) }
    }

    @Test
    fun anchorPoint_throwsIfiAnchorPointIsHigherThanOne() {
        val objectNode = Object2D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec2(1.1f, 1.1f) }
    }

}