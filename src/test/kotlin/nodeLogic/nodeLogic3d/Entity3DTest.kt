package entityLogic.entityLogic3d

import math.Vec3
import nodeLogic.nodeLogic3d.Entity3D
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Entity3DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowIfiPositionIsValid() {
        assertDoesNotThrow { Entity3D(initialPosition = Vec3(1.0f, 0.75f, 4.0f)) }
    }

    @Test
    fun constructor_throwsIfXiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Entity3D(initialPosition = Vec3(-1.0f, 0.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Entity3D(initialPosition = Vec3(0.0f, -1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfZiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Entity3D(initialPosition = Vec3(0.0f, 0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Entity3D(initialPosition = Vec3(-1.0f, -1.0f, -1.0f)) }
    }

    @Test
    fun constructor_doesNotThrowIfiAnchorPointIsValid() {
        assertDoesNotThrow { Entity3D(initialPosition = Vec3(1.0f, 0.75f, 0.5f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Entity3D(initialAnchorPoint = Vec3(-1.0f, 0.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Entity3D(initialAnchorPoint = Vec3(0.0f, -1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfZiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Entity3D(initialAnchorPoint = Vec3(0.0f, 0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Entity3D(initialAnchorPoint = Vec3(-1.0f, -1.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Entity3D(initialAnchorPoint = Vec3(1.1f, 0.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Entity3D(initialAnchorPoint = Vec3(0.0f, 1.1f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfZiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Entity3D(initialAnchorPoint = Vec3(0.0f, 0.0f, 1.1f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Entity3D(initialAnchorPoint = Vec3(1.1f, 1.1f, 1.1f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun position_doesNotThrowIfValid() {
        val entity = Entity3D()
        assertDoesNotThrow { entity.position = Vec3(1.0f, 0.3f, 435.0f) }
    }

    @Test
    fun position_throwsIfXIsNegative() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.position = Vec3(-1.0f, 1.0f, 1.0f) }
    }

    @Test
    fun position_throwsIfYIsNegative() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.position = Vec3(0.3f, -1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfZIsNegative() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.position = Vec3(0.3f, 1.0f, -1.3f) }
    }

    @Test
    fun position_throwsIfNegative() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.position = Vec3(-1.0f, -1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_doesNotThrowIfValid() {
        val entity = Entity3D()
        assertDoesNotThrow { entity.anchorPoint = Vec3(1.0f, 0.5f, 0.3f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsNegative() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec3(-1.0f, 0.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsNegative() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec3(0.0f, -1.0f, 0.3f) }
    }
    @Test
    fun anchorPoint_throwsIfZIsNegative() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec3(0.0f, 1.0f, -0.3f) }
    }

    @Test
    fun anchorPoint_throwsIfNegative() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec3(-1.0f, -1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsHigherThanOne() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec3(1.1f, 0.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsHigherThanOne() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec3(0.0f, 1.1f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfZIsHigherThanOne() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec3(0.0f, 0.0f, 1.1f) }
    }

    @Test
    fun anchorPoint_throwsIfiAnchorPointIsHigherThanOne() {
        val entity = Entity3D()
        assertThrows<IllegalArgumentException> { entity.anchorPoint = Vec3(1.1f, 1.1f, 1.1f) }
    }

}