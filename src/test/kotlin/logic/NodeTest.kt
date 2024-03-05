package logic

import logic.logic2d.Node2D
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class NodeTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowByDefault() {
        assertDoesNotThrow { Node() }
    }

    @Test
    fun constructor_doesNotThrowIfiIdIsValid() {
        assertDoesNotThrow { Node("id") }
    }

    @Test
    fun constructor_throwsIfIdIsOnlyMadeBySpaces() {
        assertThrows<IllegalArgumentException> { Node("    ") }
    }

    @Test
    fun constructor_throwsIfIdHasASpaceAsFirstChar() {
        assertThrows<IllegalArgumentException> { Node(" id") }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun id_doesNotThrowIfValid() {
        val node = Node2D()
        assertDoesNotThrow { node.id = "id" }
    }

    @Test
    fun id_throwsIfEmpty() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.id = "" }
    }

    @Test
    fun id_throwsIfOnlyMadeBySpaces() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.id = "    " }
    }

    @Test
    fun id_throwsIfHasASpaceAsFirstChar() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.id = " id" }
    }

    // ### FUNCTION TEST ###
    // ---------------------

    @Test
    fun addChild_addsTheNodeToTheChildrenList() {
        val node = Node2D()
        val childNode = Node2D("childNode")
        node.addChild(childNode)

        assertEquals(1, node.getChildrenCount())
        assertEquals(childNode, node.getChildById("childNode"))
    }

    @Test
    fun addChild_throwsIfTriedToAdd() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.addChild(node) }
    }

    @Test
    fun getChildById_returnsCorrectNode() {
        val node = Node2D()
        val childNode = Node2D("childNode")
        node.addChild(childNode)

        assertEquals(childNode, node.getChildById("childNode"))
    }

    @Test
    fun getChildById_throwsIfIdIsEmpty() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.getChildById("") }
    }

    @Test
    fun getChildById_throwsIfIdIsOnlyMadeBySpaces() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.getChildById("    ") }
    }

    @Test
    fun getChildById_throwsIfIdHasASpaceAsFirstChar() {
        val node = Node2D()
        assertThrows<IllegalArgumentException> { node.getChildById(" id") }
    }

    @Test
    fun getChildById_throwsIfNoChildIsFound() {
        val node = Node2D()
        assertThrows<Exception> { node.getChildById("id") }
    }

    @Test
    fun getChildAtIndex_removesCorrectChild() {
        val node = Node2D()
        val childNode1 = Node2D("childNode1")
        node.addChild(childNode1)
        val childNode2 = Node2D("childNode2")
        node.addChild(childNode2)
        val childNode3 = Node2D("childNode3")
        node.addChild(childNode3)
        val childNode4 = Node2D("childNode4")
        node.addChild(childNode4)

        assertEquals(childNode2, node.getChildAtIndex(1))
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = Node2D()
        val childNode1 = Node2D("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(2) }
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node2D()
        val childNode1 = Node2D("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(-1) }
    }

    @Test
    fun getChildAtIndex_throwsIfThereAreNoChildren() {
        val node = Node2D()

        assertThrows<IllegalStateException> { node.getChildAtIndex(0) }
    }

    @Test
    fun getChildrenCount_returnsCorrectChildrenCount() {
        val node = Node2D()
        val childNode1 = Node2D()
        node.addChild(childNode1)
        val childNode2 = Node2D()
        node.addChild(childNode2)
        val childNode3 = Node2D()
        node.addChild(childNode3)
        val childNode4 = Node2D()
        node.addChild(childNode4)

        assertEquals(4, node.getChildrenCount())
    }

    @Test
    fun removeChild_removesChildCorrectly() {
        val node = Node2D()
        val childNode = Node2D("childNode")
        node.addChild(childNode)

        assertEquals(childNode, node.getChildById("childNode"))
        node.removeChild(childNode)
        assertThrows<Exception> { node.getChildById("childNode") }
    }

    @Test
    fun removeChild_throwsIfChildIsNotPresent() {
        val node1 = Node2D()
        val node2 = Node2D()

        assertThrows<Exception> { node1.removeChild(node2) }
    }

    @Test
    fun removeChildById_removesChildCorrectly() {
        val node = Node2D()
        val childNode = Node2D("childNode")
        node.addChild(childNode)

        node.removeChildById("childNode")
        assertEquals(0, node.getChildrenCount())
    }

    @Test
    fun removeChildById_throwsIfChildIsNotPresent() {
        val node = Node2D()
        assertThrows<Exception> { node.removeChildById("childNode") }
    }

    @Test
    fun removeChildById_throwsIfIdIsEmpty() {
        val node = Node2D()
        val childNode = Node2D("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("") }
    }

    @Test
    fun removeChildById_throwsIfIdHasOnlySpaces() {
        val node = Node2D()
        val childNode = Node2D("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("    ") }
    }

    @Test
    fun removeChildById_throwsIfIdHasSpaceAsFirstChar() {
        val node = Node2D()
        val childNode = Node2D("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById(" id") }
    }

    @Test
    fun removeChildAtIndex_removesCorrectChild() {
        val node = Node2D()
        val childNode1 = Node2D("childNode1")
        node.addChild(childNode1)
        val childNode2 = Node2D("childNode2")
        node.addChild(childNode2)
        val childNode3 = Node2D("childNode3")
        node.addChild(childNode3)
        val childNode4 = Node2D("childNode4")
        node.addChild(childNode4)

        node.removeChildAtIndex(2)

        assertEquals(childNode1, node.getChildById("childNode1"))
        assertEquals(childNode2, node.getChildById("childNode2"))
        assertThrows<Exception> { node.getChildById("childNode3") }
        assertEquals(childNode4, node.getChildById("childNode4"))
    }

    @Test
    fun removeChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = Node2D()
        val childNode1 = Node2D("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(2) }
    }

    @Test
    fun removeChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node2D()
        val childNode1 = Node2D("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(-1) }
    }

    @Test
    fun removeChildAtIndex_throwsIfThereAreNoChildren() {
        val node = Node2D()

        assertThrows<IllegalStateException> { node.removeChildAtIndex(0) }
    }
}