package entityLogic.entityLogic2d

import math.Vec2
import nodeLogic.nodeLogic2d.Entity2D
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Entity2DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowIfiPositionIsValid() {
        assertDoesNotThrow { Entity2D(initialPosition = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Entity2D(initialPosition = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Entity2D(initialPosition = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Entity2D(initialPosition = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_doesNotThrowIfiAnchorPointIsValid() {
        assertDoesNotThrow { Entity2D(initialPosition = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Entity2D(initialAnchorPoint = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Entity2D(initialAnchorPoint = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Entity2D(initialAnchorPoint = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Entity2D(initialAnchorPoint = Vec2(1.1f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Entity2D(initialAnchorPoint = Vec2(0.0f, 1.1f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Entity2D(initialAnchorPoint = Vec2(1.1f, 1.1f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun position_doesNotThrowIfValid() {
        val entity = Entity2D()
        assertDoesNotThrow { entity.position = Vec2(1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfXIsNegative() {
        val entity = Entity2D()
        assertThrows<IllegalArgumentException> { entity.position = Vec2(-1.0f, 1.0f) }
    }

    @Test
    fun position_throwsIfYIsNegative() {
        val entity = Entity2D()
        assertThrows<IllegalArgumentException> { entity.position = Vec2(0.3f, -1.0f) }
    }

    @Test
    fun position_throwsIfNegative() {
        val entity = Entity2D()
        assertThrows<IllegalArgumentException> { entity.position = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_doesNotThrowIfValid() {
        val entity = Entity2D()
        assertDoesNotThrow { entity.anchorPoint = Vec2(1.0f, 0.5f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsNegative() {
        val entity = Entity2D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec2(-1.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsNegative() {
        val entity = Entity2D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec2(0.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfNegative() {
        val entity = Entity2D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsHigherThanOne() {
        val entity = Entity2D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec2(1.1f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsHigherThanOne() {
        val entity = Entity2D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec2(0.0f, 1.1f) }
    }

    @Test
    fun anchorPoint_throwsIfiAnchorPointIsHigherThanOne() {
        val entity = Entity2D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec2(1.1f, 1.1f) }
    }

}