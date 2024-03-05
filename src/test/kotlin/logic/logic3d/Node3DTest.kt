package logic.logic3d

import logic.logic3d.Node3D
import math.Vec3
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Node3DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowIfiPositionIsValid() {
        assertDoesNotThrow { Node3D(initialPosition = Vec3(1.0f, 0.75f, 4.0f)) }
    }

    @Test
    fun constructor_throwsIfXiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(initialPosition = Vec3(-1.0f, 0.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(initialPosition = Vec3(0.0f, -1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfZiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(initialPosition = Vec3(0.0f, 0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(initialPosition = Vec3(-1.0f, -1.0f, -1.0f)) }
    }

    @Test
    fun constructor_doesNotThrowIfiAnchorPointIsValid() {
        assertDoesNotThrow { Node3D(initialPosition = Vec3(1.0f, 0.75f, 0.5f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(initialAnchorPoint = Vec3(-1.0f, 0.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(initialAnchorPoint = Vec3(0.0f, -1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfZiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(initialAnchorPoint = Vec3(0.0f, 0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(initialAnchorPoint = Vec3(-1.0f, -1.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node3D(initialAnchorPoint = Vec3(1.1f, 0.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node3D(initialAnchorPoint = Vec3(0.0f, 1.1f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfZiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node3D(initialAnchorPoint = Vec3(0.0f, 0.0f, 1.1f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node3D(initialAnchorPoint = Vec3(1.1f, 1.1f, 1.1f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun position_doesNotThrowIfValid() {
        val node = Node3D()
        assertDoesNotThrow { node.position = Vec3(1.0f, 0.3f, 435.0f) }
    }

    @Test
    fun position_throwsIfXIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.position = Vec3(-1.0f, 1.0f, 1.0f) }
    }

    @Test
    fun position_throwsIfYIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.position = Vec3(0.3f, -1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfZIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.position = Vec3(0.3f, 1.0f, -1.3f) }
    }

    @Test
    fun position_throwsIfNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.position = Vec3(-1.0f, -1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_doesNotThrowIfValid() {
        val node = Node3D()
        assertDoesNotThrow { node.anchorPoint = Vec3(1.0f, 0.5f, 0.3f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec3(-1.0f, 0.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec3(0.0f, -1.0f, 0.3f) }
    }
    @Test
    fun anchorPoint_throwsIfZIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec3(0.0f, 1.0f, -0.3f) }
    }

    @Test
    fun anchorPoint_throwsIfNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec3(-1.0f, -1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsHigherThanOne() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec3(1.1f, 0.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsHigherThanOne() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec3(0.0f, 1.1f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfZIsHigherThanOne() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec3(0.0f, 0.0f, 1.1f) }
    }

    @Test
    fun anchorPoint_throwsIfiAnchorPointIsHigherThanOne() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec3(1.1f, 1.1f, 1.1f) }
    }

}