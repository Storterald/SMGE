package nodeLogic.nodeLogic2d

import math.Vec2
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Node2DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowIfPositionIsValid() {
        assertDoesNotThrow { Node2D(position = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(position = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(position = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfPositionIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(position = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_doesNotThrowIfAnchorPointIsValid() {
        assertDoesNotThrow { Node2D(position = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfXAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vec2(1.1f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vec2(0.0f, 1.1f)) }
    }

    @Test
    fun constructor_throwsIfAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vec2(1.1f, 1.1f)) }
    }

    @Test
    fun constructor_doesNotThrowIfScaleIsValid() {
        assertDoesNotThrow { Node2D(scale = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(scale = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(scale = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(scale = Vec2(-1.0f, -1.0f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun position_doesNotThrowIfValid() {
        val node = Node2D()
        assertDoesNotThrow { node.position = Vec2(1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfXValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.position = Vec2(-1.0f, 1.0f) }
    }

    @Test
    fun position_throwsIfYValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.position = Vec2(0.3f, -1.0f) }
    }

    @Test
    fun position_throwsIfValuesAreNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.position = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_doesNotThrowIfValid() {
        val node = Node2D()
        assertDoesNotThrow { node.anchorPoint = Vec2(1.0f, 0.5f) }
    }

    @Test
    fun anchorPoint_throwsIfXValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(-1.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(0.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfValuesAreNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfXValueIsHigherThanOne() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(1.1f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYValuesAreHigherThanOne() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(0.0f, 1.1f) }
    }

    @Test
    fun anchorPoint_throwsIfValueIsHigherThanOne() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(1.1f, 1.1f) }
    }

    @Test
    fun scale_doesNotThrowIfScaleIsValid() {
        val node = Node2D()
        assertDoesNotThrow { node.scale = Vec2(1.0f, 0.75f) }
    }

    @Test
    fun scale_throwsIfXValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.scale = Vec2(-1.0f, 0.0f) }
    }

    @Test
    fun scale_throwsIfYValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.scale = Vec2(0.0f, -1.0f) }
    }

    @Test
    fun scale_throwsIfValuesAreNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.scale = Vec2(-1.0f, -1.0f) }
    }

}