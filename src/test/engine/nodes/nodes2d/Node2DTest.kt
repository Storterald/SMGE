package nodes.nodes2d

import org.joml.Vector2f
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Node2DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test fun constructor_doesNotThrowIfAnchorPointIsValid() {
        assertDoesNotThrow { Node2D(position = Vector2f(1.0f, 0.75f)) }
    }

    @Test fun constructor_throwsIfXAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vector2f(-1.0f, 0.0f)) }
    }

    @Test fun constructor_throwsIfYAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vector2f(0.0f, -1.0f)) }
    }

    @Test fun constructor_throwsIfAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vector2f(-1.0f, -1.0f)) }
    }

    @Test fun constructor_throwsIfXAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vector2f(1.1f, 0.0f)) }
    }

    @Test fun constructor_throwsIfYAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vector2f(0.0f, 1.1f)) }
    }

    @Test fun constructor_throwsIfAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node2D(anchorPoint = Vector2f(1.1f, 1.1f)) }
    }

    @Test fun constructor_doesNotThrowIfScaleIsValid() {
        assertDoesNotThrow { Node2D(scale = Vector2f(1.0f, 0.75f)) }
    }

    @Test fun constructor_throwsIfXScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(scale = Vector2f(-1.0f, 0.0f)) }
    }

    @Test fun constructor_throwsIfYScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(scale = Vector2f(0.0f, -1.0f)) }
    }

    @Test fun constructor_throwsIfScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(scale = Vector2f(-1.0f, -1.0f)) }
    }

    @Test fun constructor_throwsIfXSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(size = Vector2f(-1.0f, 0.0f)) }
    }

    @Test fun constructor_throwsIfYSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(size = Vector2f(0.0f, -1.0f)) }
    }

    @Test fun constructor_throwsIfSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Node2D(size = Vector2f(-1.0f, -1.0f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test fun anchorPoint_doesNotThrowIfValid() {
        val node = Node2D()
        assertDoesNotThrow { node.anchorPoint = Vector2f(1.0f, 0.5f) }
    }

    @Test fun anchorPoint_throwsIfXValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector2f(-1.0f, 0.0f) }
    }

    @Test fun anchorPoint_throwsIfYValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector2f(0.0f, -1.0f) }
    }

    @Test fun anchorPoint_throwsIfValuesAreNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector2f(-1.0f, -1.0f) }
    }

    @Test fun anchorPoint_throwsIfXValueIsHigherThanOne() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector2f(1.1f, 0.0f) }
    }

    @Test fun anchorPoint_throwsIfYValuesAreHigherThanOne() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector2f(0.0f, 1.1f) }
    }

    @Test fun anchorPoint_throwsIfValueIsHigherThanOne() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector2f(1.1f, 1.1f) }
    }

    @Test fun scale_doesNotThrowIfScaleIsValid() {
        val node = Node2D()
        assertDoesNotThrow { node.scale = Vector2f(1.0f, 0.75f) }
    }

    @Test fun scale_throwsIfXValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.scale = Vector2f(-1.0f, 0.0f) }
    }

    @Test fun scale_throwsIfYValueIsNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.scale = Vector2f(0.0f, -1.0f) }
    }

    @Test fun scale_throwsIfValuesAreNegative() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.scale = Vector2f(-1.0f, -1.0f) }
    }

    @Test fun size_doesNotThrowIfValid() {
        val node2D = Node2D()
        assertDoesNotThrow { node2D.size = Vector2f(1.0f, 1.0f) }
    }

    @Test fun size_throwsIfXValueIsNegative() {
        val node2D = Node2D()
        assertThrows<IllegalArgumentException> { node2D.size = Vector2f(-1.0f, 0.0f) }
    }

    @Test fun size_throwsIfYValueIsNegative() {
        val node2D = Node2D()
        assertThrows<IllegalArgumentException> { node2D.size = Vector2f(0.0f, -1.0f) }
    }

    @Test fun size_throwsIfValuesAreNegative() {
        val node2D = Node2D()
        assertThrows<IllegalArgumentException> { node2D.size = Vector2f(-1.0f, -1.0f) }
    }

    @Test fun scaledSize_calculatesScaledSize() {
        val node2D = Node2D(size = Vector2f(100.0f, 100.0f), scale = Vector2f(0.5f, 0.5f))
        assertEquals(50.0f, node2D.scaledSize.x)
        assertEquals(50.0f, node2D.scaledSize.y)
    }

    @Test fun anchoredPosition_calculatesAnchoredPosition() {
        val node2D = Node2D(size = Vector2f(100.0f, 100.0f), position = Vector2f(100.0f, 100.0f), anchorPoint = Vector2f(0.5f, 0.25f))
        assertEquals(50.0f, node2D.anchoredPosition.x)
        assertEquals(75.0f, node2D.anchoredPosition.y)
    }

    @Test fun absolutePosition_calculatesAbsolutePosition() {
        val node2D = Node2D(size = Vector2f(100.0f, 100.0f), position = Vector2f(100.0f, 100.0f), anchorPoint = Vector2f(0.25f, 0.50f), scale = Vector2f(0.5f, 0.5f))
        assertEquals(87.5f, node2D.absolutePosition.x1)
        assertEquals(75.0f, node2D.absolutePosition.y1)
        assertEquals(137.5f, node2D.absolutePosition.x2)
        assertEquals(125.0f, node2D.absolutePosition.y2)
    }

}