package logic

import math.Vec2
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class GenericNode2dTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowByDefault() {
        assertDoesNotThrow { GenericNode2d() }
    }

    @Test
    fun constructor_doesNotThrowIfiIdIsValid() {
        assertDoesNotThrow { GenericNode2d("id") }
    }

    @Test
    fun constructor_throwsIfIdIsOnlyMadeBySpaces() {
        assertThrows<IllegalArgumentException> { GenericNode2d("    ") }
    }

    @Test
    fun constructor_throwsIfIdHasASpaceAsFirstChar() {
        assertThrows<IllegalArgumentException> { GenericNode2d(" id") }
    }

    @Test
    fun constructor_doesNotThrowIfiPositionIsValid() {
        assertDoesNotThrow { GenericNode2d(iPosition = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode2d(iPosition = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode2d(iPosition = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiPositionIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode2d(iPosition = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_doesNotThrowIfiAnchorPointIsValid() {
        assertDoesNotThrow { GenericNode2d(iPosition = Vec2(1.0f, 0.75f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode2d(iAnchorPoint = Vec2(-1.0f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode2d(iAnchorPoint = Vec2(0.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { GenericNode2d(iAnchorPoint = Vec2(-1.0f, -1.0f)) }
    }

    @Test
    fun constructor_throwsIfXiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { GenericNode2d(iAnchorPoint = Vec2(1.1f, 0.0f)) }
    }

    @Test
    fun constructor_throwsIfYiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { GenericNode2d(iAnchorPoint = Vec2(0.0f, 1.1f)) }
    }

    @Test
    fun constructor_throwsIfiAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { GenericNode2d(iAnchorPoint = Vec2(1.1f, 1.1f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun id_doesNotThrowIfValid() {
        val node = GenericNode2d()
        assertDoesNotThrow { node.id = "id" }
    }

    @Test
    fun id_throwsIfEmpty() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.id = "" }
    }

    @Test
    fun id_throwsIfOnlyMadeBySpaces() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.id = "    " }
    }

    @Test
    fun id_throwsIfHasASpaceAsFirstChar() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.id = " id" }
    }

    @Test
    fun position_doesNotThrowIfValid() {
        val node = GenericNode2d()
        assertDoesNotThrow { node.position = Vec2(1.0f, 0.3f) }
    }

    @Test
    fun position_throwsIfXIsNegative() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.position = Vec2(-1.0f, 1.0f) }
    }

    @Test
    fun position_throwsIfYIsNegative() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.position = Vec2(0.3f, -1.0f) }
    }

    @Test
    fun position_throwsIfNegative() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.position = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_doesNotThrowIfValid() {
        val node = GenericNode2d()
        assertDoesNotThrow { node.anchorPoint = Vec2(1.0f, 0.5f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsNegative() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(-1.0f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsNegative() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(0.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfNegative() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(-1.0f, -1.0f) }
    }

    @Test
    fun anchorPoint_throwsIfXIsHigherThanOne() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(1.1f, 0.0f) }
    }

    @Test
    fun anchorPoint_throwsIfYIsHigherThanOne() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(0.0f, 1.1f) }
    }

    @Test
    fun anchorPoint_throwsIfiAnchorPointIsHigherThanOne() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vec2(1.1f, 1.1f) }
    }

    // ### FUNCTION TEST ###
    // ---------------------

    @Test
    fun addChild_addsTheNodeToTheChildrenList() {
        val node = GenericNode2d()
        val childNode = GenericNode2d("childNode")
        node.addChild(childNode)

        assertEquals(1, node.getChildrenCount())
        assertEquals(childNode, node.getChildById("childNode"))
    }

    @Test
    fun addChild_throwsIfTriedToAdd() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.addChild(node) }
    }

    @Test
    fun getChildById_returnsCorrectNode() {
        val node = GenericNode2d()
        val childNode = GenericNode2d("childNode")
        node.addChild(childNode)

        assertEquals(childNode, node.getChildById("childNode"))
    }

    @Test
    fun getChildById_throwsIfIdIsEmpty() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.getChildById("") }
    }

    @Test
    fun getChildById_throwsIfIdIsOnlyMadeBySpaces() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.getChildById("    ") }
    }

    @Test
    fun getChildById_throwsIfIdHasASpaceAsFirstChar() {
        val node = GenericNode2d()
        assertThrows<IllegalArgumentException> { node.getChildById(" id") }
    }

    @Test
    fun getChildById_throwsIfNoChildIsFound() {
        val node = GenericNode2d()
        assertThrows<Exception> { node.getChildById("id") }
    }

    @Test
    fun getChildAtIndex_removesCorrectChild() {
        val node = GenericNode2d()
        val childNode1 = GenericNode2d("childNode1")
        node.addChild(childNode1)
        val childNode2 = GenericNode2d("childNode2")
        node.addChild(childNode2)
        val childNode3 = GenericNode2d("childNode3")
        node.addChild(childNode3)
        val childNode4 = GenericNode2d("childNode4")
        node.addChild(childNode4)

        assertEquals(childNode2, node.getChildAtIndex(1))
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = GenericNode2d()
        val childNode1 = GenericNode2d("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(2) }
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsNegative() {
        val node = GenericNode2d()
        val childNode1 = GenericNode2d("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(-1) }
    }

    @Test
    fun getChildAtIndex_throwsIfThereAreNoChildren() {
        val node = GenericNode2d()

        assertThrows<IllegalStateException> { node.getChildAtIndex(0) }
    }

    @Test
    fun getChildrenCount_returnsCorrectChildrenCount() {
        val node = GenericNode2d()
        val childNode1 = GenericNode2d()
        node.addChild(childNode1)
        val childNode2 = GenericNode2d()
        node.addChild(childNode2)
        val childNode3 = GenericNode2d()
        node.addChild(childNode3)
        val childNode4 = GenericNode2d()
        node.addChild(childNode4)

        assertEquals(4, node.getChildrenCount())
    }

    @Test
    fun removeChild_removesChildCorrectly() {
        val node = GenericNode2d()
        val childNode = GenericNode2d("childNode")
        node.addChild(childNode)

        assertEquals(childNode, node.getChildById("childNode"))
        node.removeChild(childNode)
        assertThrows<Exception> { node.getChildById("childNode") }
    }

    @Test
    fun removeChild_throwsIfChildIsNotPresent() {
        val node1 = GenericNode2d()
        val node2 = GenericNode2d()

        assertThrows<Exception> { node1.removeChild(node2) }
    }

    @Test
    fun removeChildById_removesChildCorrectly() {
        val node = GenericNode2d()
        val childNode = GenericNode2d("childNode")
        node.addChild(childNode)

        node.removeChildById("childNode")
        assertEquals(0, node.getChildrenCount())
    }

    @Test
    fun removeChildById_throwsIfChildIsNotPresent() {
        val node = GenericNode2d()
        assertThrows<Exception> { node.removeChildById("childNode") }
    }

    @Test
    fun removeChildById_throwsIfIdIsEmpty() {
        val node = GenericNode2d()
        val childNode = GenericNode2d("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("") }
    }

    @Test
    fun removeChildById_throwsIfIdHasOnlySpaces() {
        val node = GenericNode2d()
        val childNode = GenericNode2d("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("    ") }
    }

    @Test
    fun removeChildById_throwsIfIdHasSpaceAsFirstChar() {
        val node = GenericNode2d()
        val childNode = GenericNode2d("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById(" id") }
    }

    @Test
    fun removeChildAtIndex_removesCorrectChild() {
        val node = GenericNode2d()
        val childNode1 = GenericNode2d("childNode1")
        node.addChild(childNode1)
        val childNode2 = GenericNode2d("childNode2")
        node.addChild(childNode2)
        val childNode3 = GenericNode2d("childNode3")
        node.addChild(childNode3)
        val childNode4 = GenericNode2d("childNode4")
        node.addChild(childNode4)

        node.removeChildAtIndex(2)

        assertEquals(childNode1, node.getChildById("childNode1"))
        assertEquals(childNode2, node.getChildById("childNode2"))
        assertThrows<Exception> { node.getChildById("childNode3") }
        assertEquals(childNode4, node.getChildById("childNode4"))
    }

    @Test
    fun removeChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = GenericNode2d()
        val childNode1 = GenericNode2d("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(2) }
    }

    @Test
    fun removeChildAtIndex_throwsIfIndexIsNegative() {
        val node = GenericNode2d()
        val childNode1 = GenericNode2d("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(-1) }
    }

    @Test
    fun removeChildAtIndex_throwsIfThereAreNoChildren() {
        val node = GenericNode2d()

        assertThrows<IllegalStateException> { node.removeChildAtIndex(0) }
    }

}