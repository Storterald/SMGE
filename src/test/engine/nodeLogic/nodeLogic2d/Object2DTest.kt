package nodeLogic.nodeLogic2d

import org.joml.Vector2f
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class Object2DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowWithDefaultParameters() {
        assertDoesNotThrow { Object2D() }
    }

    @Test
    fun constructor_throwsIfXSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Object2D(size = Vector2f(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Object2D(size = Vector2f(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfSizeIsNegative() {
        assertThrows<IllegalArgumentException> { Object2D(size = Vector2f(-1.0f, -1.0f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun size_doesNotThrowIfValid() {
        val object2D = Object2D()
        assertDoesNotThrow { object2D.size = Vector2f(1.0f, 1.0f) }
    }

    @Test
    fun size_throwsIfXValueIsNegative() {
        val object2D = Object2D()
        assertThrows<IllegalArgumentException> { object2D.size = Vector2f(-1.0f, 0.0f) }
    }

    @Test
    fun size_throwsIfYValueIsNegative() {
        val object2D = Object2D()
        assertThrows<IllegalArgumentException> { object2D.size = Vector2f(0.0f, -1.0f) }
    }

    @Test
    fun size_throwsIfValuesAreNegative() {
        val object2D = Object2D()
        assertThrows<IllegalArgumentException> { object2D.size = Vector2f(-1.0f, -1.0f) }
    }

    @Test
    fun scaledSize_calculatesScaledSize() {
        val object2D = Object2D(size = Vector2f(100.0f, 100.0f), scale = Vector2f(0.5f, 0.5f))
        assertEquals(50.0f, object2D.scaledSize.x)
        assertEquals(50.0f, object2D.scaledSize.y)
    }

    @Test
    fun anchoredPosition_calculatesAnchoredPosition() {
        val object2D = Object2D(size = Vector2f(100.0f, 100.0f), position = Vector2f(100.0f, 100.0f), anchorPoint = Vector2f(0.5f, 0.25f))
        assertEquals(50.0f, object2D.anchoredPosition.x)
        assertEquals(75.0f, object2D.anchoredPosition.y)
    }

    @Test
    fun absolutePosition_calculatesAbsolutePosition() {
        val object2D = Object2D(size = Vector2f(100.0f, 100.0f), position = Vector2f(100.0f, 100.0f), anchorPoint = Vector2f(0.25f, 0.50f), scale = Vector2f(0.5f, 0.5f))
        assertEquals(87.5f, object2D.absolutePosition.x1)
        assertEquals(75.0f, object2D.absolutePosition.y1)
        assertEquals(137.5f, object2D.absolutePosition.x2)
        assertEquals(125.0f, object2D.absolutePosition.y2)
    }

}