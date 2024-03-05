package logic.logic2d

import logic.logic2d.Node2D
import math.Vec2
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Node2DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowIfiPositionIsValid() {
        assertDoesNotThrow { Node2D(initialPosition = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(initialPosition = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(initialPosition = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(initialPosition = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_doesNotThrowIfiAnchorPointIsValid() {
        assertDoesNotThrow { Node2D(initialPosition = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(initialAnchorPoint = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(initialAnchorPoint = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(initialAnchorPoint = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node2D(initialAnchorPoint = Vec2(1.1f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node2D(initialAnchorPoint = Vec2(0.0f, 1.1f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node2D(initialAnchorPoint = Vec2(1.1f, 1.1f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun position_doesNotThrowIfValid() {
        val node = Node2D()
        assertDoesNotThrow { node.position = Vec2(1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfXIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.position = Vec2(-1.0f, 1.0f) }
    }

    @Test
    fun position_throwsIfYIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.position = Vec2(0.3f, -1.0f) }
    }

    @Test
    fun position_throwsIfNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.position = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_doesNotThrowIfValid() {
        val node = Node2D()
        assertDoesNotThrow { node.anchorPoint = Vec2(1.0f, 0.5f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(-1.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(0.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsHigherThanOne() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(1.1f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsHigherThanOne() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(0.0f, 1.1f) }
    }

    @Test
    fun anchorPoint_throwsIfiAnchorPointIsHigherThanOne() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(1.1f, 1.1f) }
    }

}