package logic

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*

class GenericNodeTest {

    // ### CONSTRUCTOR TEST ###

    @Test
    fun constructor_doesNotThrowByDefault() {
        assertDoesNotThrow { GenericNode() }
    }

    @Test
    fun constructor_doesNotThrowIfiIdIsValid() {
        assertDoesNotThrow { GenericNode("id") }
    }

    @Test
    fun constructor_throwsIfIdIsOnlyMadeBySpaces() {
        assertThrows<IllegalArgumentException> { GenericNode("    ") }
    }

    @Test
    fun constructor_throwsIfIdHasASpaceAsFirstChar() {
        assertThrows<IllegalArgumentException> { GenericNode(" id") }
    }

    @Test
    fun constructor_doesNotThrowIfiPositionIsValid() {
        assertDoesNotThrow { GenericNode(iPosition = floatArrayOf(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfiPositionHasMoreThanTwoValues() {
        assertThrows<IllegalArgumentException> { GenericNode(iPosition = floatArrayOf(1.0f, 0.0f, 0.3f)) }
    }

    @Test
    fun constructor_throwsIfiPositionHasOnlyOneValue() {
        assertThrows<IllegalArgumentException> { GenericNode(iPosition = floatArrayOf(1.0f)) }
    }

    @Test
    fun constructor_throwsIfiPositionHasNoValues() {
        assertThrows<IllegalArgumentException> { GenericNode(iPosition = floatArrayOf()) }
    }

    @Test
    fun constructor_throwsIfXiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode(iPosition = floatArrayOf(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode(iPosition = floatArrayOf(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode(iPosition = floatArrayOf(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_doesNotThrowIfiAnchorPointIsValid() {
        assertDoesNotThrow { GenericNode(iPosition = floatArrayOf(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointHasMoreThanTwoValues() {
        assertThrows<IllegalArgumentException> { GenericNode(iAnchorPoint = floatArrayOf(1.0f, 0.0f, 0.3f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointHasOnlyOneValue() {
        assertThrows<IllegalArgumentException> { GenericNode(iAnchorPoint = floatArrayOf(1.0f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointHasNoValues() {
        assertThrows<IllegalArgumentException> { GenericNode(iAnchorPoint = floatArrayOf()) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode(iAnchorPoint = floatArrayOf(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode(iAnchorPoint = floatArrayOf(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode(iAnchorPoint = floatArrayOf(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { GenericNode(iAnchorPoint = floatArrayOf(1.1f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { GenericNode(iAnchorPoint = floatArrayOf(0.0f, 1.1f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { GenericNode(iAnchorPoint = floatArrayOf(1.1f, 1.1f)) }
    }

    // ### SETTERS TEST ###

    @Test
    fun id_doesNotThrowIfValid() {
        val node = GenericNode()
        assertDoesNotThrow { node.id = "id" }
    }

    @Test
    fun id_throwsIfEmpty() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.id = "" }
    }

    @Test
    fun id_throwsIfOnlyMadeBySpaces() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.id = "    " }
    }

    @Test
    fun id_throwsIfHasASpaceAsFirstChar() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.id = " id" }
    }

    @Test
    fun position_doesNotThrowIfValid() {
        val node = GenericNode()
        assertDoesNotThrow { node.position = floatArrayOf(1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfHasMoreThanTwoValues() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.position = floatArrayOf(1.0f, 1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfHasOnlyOneValue() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.position = floatArrayOf(1.0f) }
    }

    @Test
    fun position_throwsIfHasNoValues() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.position = floatArrayOf() }
    }

    @Test
    fun position_throwsIfXIsNegative() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.position = floatArrayOf(-1.0f, 1.0f) }
    }

    @Test
    fun position_throwsIfYIsNegative() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.position = floatArrayOf(0.3f, -1.0f) }
    }

    @Test
    fun position_throwsIfNegative() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.position = floatArrayOf(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_doesNotThrowIfValid() {
        val node = GenericNode()
        assertDoesNotThrow { node.anchorPoint = floatArrayOf(1.0f, 0.5f) }
    }

    @Test
    fun anchorPoint_throwsIftHasMoreThanTwoValues() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.anchorPoint = floatArrayOf(1.0f, 0.3f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfHasOnlyOneValue() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.anchorPoint = floatArrayOf(0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfHasNoValues() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.anchorPoint = floatArrayOf() }
    }

    @Test
    fun anchorPoint_throwsIfXIsNegative() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.anchorPoint = floatArrayOf(-1.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsNegative() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.anchorPoint = floatArrayOf(0.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfNegative() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.anchorPoint = floatArrayOf(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsHigherThanOne() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.anchorPoint = floatArrayOf(1.1f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsHigherThanOne() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.anchorPoint = floatArrayOf(0.0f, 1.1f) }
    }

    @Test
    fun anchorPoint_throwsIfiAnchorPointIsHigherThanOne() {
        val node = GenericNode()
        assertThrows<IllegalArgumentException> { node.anchorPoint = floatArrayOf(1.1f, 1.1f) }
    }

}