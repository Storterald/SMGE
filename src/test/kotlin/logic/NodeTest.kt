package logic

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
        val node = Node()
        assertDoesNotThrow { node.id = "id" }
    }

    @Test
    fun id_throwsIfEmpty() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.id = "" }
    }

    @Test
    fun id_throwsIfOnlyMadeBySpaces() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.id = "    " }
    }

    @Test
    fun id_throwsIfHasASpaceAsFirstChar() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.id = " id" }
    }

    // ### FUNCTION TEST ###
    // ---------------------

    @Test
    fun addChild_worksCorrectly() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertEquals(1, node.getChildrenCount())
        assertEquals(childNode, node.getChildById("childNode"))
        assertEquals(node, childNode.parent)
    }

    @Test
    fun addChild_throwsIfTriedToAdd() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.addChild(node) }
    }

    @Test
    fun getChildById_returnsCorrectNode() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertEquals(childNode, node.getChildById("childNode"))
    }

    @Test
    fun getChildById_throwsIfIdIsEmpty() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.getChildById("") }
    }

    @Test
    fun getChildById_throwsIfIdIsOnlyMadeBySpaces() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.getChildById("    ") }
    }

    @Test
    fun getChildById_throwsIfIdHasASpaceAsFirstChar() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.getChildById(" id") }
    }

    @Test
    fun getChildById_throwsIfNoChildIsFound() {
        val node = Node()
        assertThrows<Exception> { node.getChildById("id") }
    }

    @Test
    fun getChildAtIndex_removesCorrectChild() {
        val node = Node()
        val childNode1 = Node("childNode1")
        node.addChild(childNode1)
        val childNode2 = Node("childNode2")
        node.addChild(childNode2)
        val childNode3 = Node("childNode3")
        node.addChild(childNode3)
        val childNode4 = Node("childNode4")
        node.addChild(childNode4)

        assertEquals(childNode2, node.getChildAtIndex(1))
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = Node()
        val childNode1 = Node("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(2) }
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node()
        val childNode1 = Node("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(-1) }
    }

    @Test
    fun getChildAtIndex_throwsIfThereAreNoChildren() {
        val node = Node()

        assertThrows<IllegalStateException> { node.getChildAtIndex(0) }
    }

    @Test
    fun getChildrenCount_returnsCorrectChildrenCount() {
        val node = Node()
        val childNode1 = Node()
        node.addChild(childNode1)
        val childNode2 = Node()
        node.addChild(childNode2)
        val childNode3 = Node()
        node.addChild(childNode3)
        val childNode4 = Node()
        node.addChild(childNode4)

        assertEquals(4, node.getChildrenCount())
    }

    @Test
    fun removeChild_removesChildCorrectly() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        node.removeChild(childNode)
        assertEquals(0, node.getChildrenCount())
        assertEquals(null, childNode.parent)
    }

    @Test
    fun removeChild_throwsIfChildIsNotPresent() {
        val node1 = Node()
        val node2 = Node()

        assertThrows<Exception> { node1.removeChild(node2) }
    }

    @Test
    fun removeChildById_removesChildCorrectly() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        node.removeChildById("childNode")
        assertEquals(0, node.getChildrenCount())
        assertEquals(null, childNode.parent)
    }

    @Test
    fun removeChildById_throwsIfChildIsNotPresent() {
        val node = Node()
        assertThrows<Exception> { node.removeChildById("childNode") }
    }

    @Test
    fun removeChildById_throwsIfIdIsEmpty() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("") }
    }

    @Test
    fun removeChildById_throwsIfIdHasOnlySpaces() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("    ") }
    }

    @Test
    fun removeChildById_throwsIfIdHasSpaceAsFirstChar() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById(" id") }
    }

    @Test
    fun removeChildAtIndex_removesCorrectChild() {
        val node = Node()
        val childNode1 = Node("childNode1")
        node.addChild(childNode1)
        val childNode2 = Node("childNode2")
        node.addChild(childNode2)
        val childNode3 = Node("childNode3")
        node.addChild(childNode3)
        val childNode4 = Node("childNode4")
        node.addChild(childNode4)

        node.removeChildAtIndex(2)

        assertEquals(childNode1, node.getChildById("childNode1"))
        assertEquals(childNode2, node.getChildById("childNode2"))
        assertThrows<Exception> { node.getChildById("childNode3") }
        assertEquals(null, childNode3.parent)
        assertEquals(childNode4, node.getChildById("childNode4"))
    }

    @Test
    fun removeChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(2) }
    }

    @Test
    fun removeChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(-1) }
    }

    @Test
    fun removeChildAtIndex_throwsIfThereAreNoChildren() {
        val node = Node()

        assertThrows<IllegalStateException> { node.removeChildAtIndex(0) }
    }
}
