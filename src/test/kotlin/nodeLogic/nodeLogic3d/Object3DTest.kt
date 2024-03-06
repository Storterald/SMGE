package objectNodeLogic.objectNodeLogic3d

import math.Vec3
import nodeLogic.nodeLogic3d.Object3D
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Object3DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowIfiPositionIsValid() {
        assertDoesNotThrow { Object3D(initialPosition = Vec3(1.0f, 0.75f, 4.0f)) }
    }

    @Test
    fun constructor_throwsIfXiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Object3D(initialPosition = Vec3(-1.0f, 0.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Object3D(initialPosition = Vec3(0.0f, -1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfZiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Object3D(initialPosition = Vec3(0.0f, 0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Object3D(initialPosition = Vec3(-1.0f, -1.0f, -1.0f)) }
    }

    @Test
    fun constructor_doesNotThrowIfiAnchorPointIsValid() {
        assertDoesNotThrow { Object3D(initialPosition = Vec3(1.0f, 0.75f, 0.5f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Object3D(initialAnchorPoint = Vec3(-1.0f, 0.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Object3D(initialAnchorPoint = Vec3(0.0f, -1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfZiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Object3D(initialAnchorPoint = Vec3(0.0f, 0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Object3D(initialAnchorPoint = Vec3(-1.0f, -1.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Object3D(initialAnchorPoint = Vec3(1.1f, 0.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Object3D(initialAnchorPoint = Vec3(0.0f, 1.1f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfZiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Object3D(initialAnchorPoint = Vec3(0.0f, 0.0f, 1.1f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Object3D(initialAnchorPoint = Vec3(1.1f, 1.1f, 1.1f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun position_doesNotThrowIfValid() {
        val objectNode = Object3D()
        assertDoesNotThrow { objectNode.position = Vec3(1.0f, 0.3f, 435.0f) }
    }

    @Test
    fun position_throwsIfXIsNegative() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.position = Vec3(-1.0f, 1.0f, 1.0f) }
    }

    @Test
    fun position_throwsIfYIsNegative() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.position = Vec3(0.3f, -1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfZIsNegative() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.position = Vec3(0.3f, 1.0f, -1.3f) }
    }

    @Test
    fun position_throwsIfNegative() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.position = Vec3(-1.0f, -1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_doesNotThrowIfValid() {
        val objectNode = Object3D()
        assertDoesNotThrow { objectNode.anchorPoint = Vec3(1.0f, 0.5f, 0.3f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsNegative() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec3(-1.0f, 0.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsNegative() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec3(0.0f, -1.0f, 0.3f) }
    }
    @Test
    fun anchorPoint_throwsIfZIsNegative() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec3(0.0f, 1.0f, -0.3f) }
    }

    @Test
    fun anchorPoint_throwsIfNegative() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec3(-1.0f, -1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsHigherThanOne() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec3(1.1f, 0.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsHigherThanOne() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec3(0.0f, 1.1f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfZIsHigherThanOne() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec3(0.0f, 0.0f, 1.1f) }
    }

    @Test
    fun anchorPoint_throwsIfiAnchorPointIsHigherThanOne() {
        val objectNode = Object3D()
        assertThrows<IllegalArgumentException> { objectNode.anchorPoint = Vec3(1.1f, 1.1f, 1.1f) }
    }

}