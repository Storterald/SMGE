package nodeLogic

import nodeLogic.nodeLogic3d.Node3D
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NodeTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_doesNotThrowByDefault() {
        assertDoesNotThrow { Node3D() }
    }

    @Test
    fun constructor_doesNotThrowIfiIdIsValid() {
        assertDoesNotThrow { Node3D("id") }
    }

    @Test
    fun constructor_throwsIfIdIsOnlyMadeBySpaces() {
        assertThrows<IllegalArgumentException> { Node3D("    ") }
    }

    @Test
    fun constructor_throwsIfIdHasASpaceAsFirstChar() {
        assertThrows<IllegalArgumentException> { Node3D(" id") }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun id_doesNotThrowIfValid() {
        val node = Node3D()
        assertDoesNotThrow { node.id = "id" }
    }

    @Test
    fun id_throwsIfEmpty() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.id = "" }
    }

    @Test
    fun id_throwsIfOnlyMadeBySpaces() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.id = "    " }
    }

    @Test
    fun id_throwsIfHasASpaceAsFirstChar() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.id = " id" }
    }

    // ### FUNCTION TEST ###
    // ---------------------

    @Test
    fun addChild_worksCorrectly() {
        val node = Node3D()
        val childNode3D = Node3D("childNode3D")
        node.addChild(childNode3D)

        assertEquals(1, node.getChildrenCount())
        assertEquals(childNode3D, node.getChildById("childNode3D"))
        assertEquals(node, childNode3D.parent)
    }

    @Test
    fun addChild_throwsIfTriedToAdd() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.addChild(node) }
    }

    @Test
    fun getChildById_returnsCorrectNode3D() {
        val node = Node3D()
        val childNode3D = Node3D("childNode3D")
        node.addChild(childNode3D)

        assertEquals(childNode3D, node.getChildById("childNode3D"))
    }

    @Test
    fun getChildById_throwsIfIdIsEmpty() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.getChildById("") }
    }

    @Test
    fun getChildById_throwsIfIdIsOnlyMadeBySpaces() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.getChildById("    ") }
    }

    @Test
    fun getChildById_throwsIfIdHasASpaceAsFirstChar() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.getChildById(" id") }
    }

    @Test
    fun getChildById_throwsIfNoChildIsFound() {
        val node = Node3D()
        assertThrows<Exception> { node.getChildById("id") }
    }

    @Test
    fun getChildAtIndex_removesCorrectChild() {
        val node = Node3D()
        val childNode3D1 = Node3D("childNode3D1")
        node.addChild(childNode3D1)
        val childNode3D2 = Node3D("childNode3D2")
        node.addChild(childNode3D2)
        val childNode3D3 = Node3D("childNode3D3")
        node.addChild(childNode3D3)
        val childNode3D4 = Node3D("childNode3D4")
        node.addChild(childNode3D4)

        assertEquals(childNode3D2, node.getChildAtIndex(1))
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = Node3D()
        val childNode3D1 = Node3D("childNode3D1")
        node.addChild(childNode3D1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(2) }
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node3D()
        val childNode3D1 = Node3D("childNode3D1")
        node.addChild(childNode3D1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(-1) }
    }

    @Test
    fun getChildAtIndex_throwsIfThereAreNoChildren() {
        val node = Node3D()

        assertThrows<IllegalStateException> { node.getChildAtIndex(0) }
    }

    @Test
    fun getChildrenCount_returnsCorrectChildrenCount() {
        val node = Node3D()
        val childNode3D1 = Node3D()
        node.addChild(childNode3D1)
        val childNode3D2 = Node3D()
        node.addChild(childNode3D2)
        val childNode3D3 = Node3D()
        node.addChild(childNode3D3)
        val childNode3D4 = Node3D()
        node.addChild(childNode3D4)

        assertEquals(4, node.getChildrenCount())
    }

    @Test
    fun removeChild_removesChildCorrectly() {
        val node = Node3D()
        val childNode3D = Node3D("childNode3D")
        node.addChild(childNode3D)

        node.removeChild(childNode3D)
        assertEquals(0, node.getChildrenCount())
        assertEquals(null, childNode3D.parent)
    }

    @Test
    fun removeChild_throwsIfChildIsNotPresent() {
        val node1 = Node3D()
        val node2 = Node3D()

        assertThrows<Exception> { node1.removeChild(node2) }
    }

    @Test
    fun removeChildById_removesChildCorrectly() {
        val node = Node3D()
        val childNode3D = Node3D("childNode3D")
        node.addChild(childNode3D)

        node.removeChildById("childNode3D")
        assertEquals(0, node.getChildrenCount())
        assertEquals(null, childNode3D.parent)
    }

    @Test
    fun removeChildById_throwsIfChildIsNotPresent() {
        val node = Node3D()
        assertThrows<Exception> { node.removeChildById("childNode3D") }
    }

    @Test
    fun removeChildById_throwsIfIdIsEmpty() {
        val node = Node3D()
        val childNode3D = Node3D("childNode3D")
        node.addChild(childNode3D)

        assertThrows<IllegalArgumentException> { node.removeChildById("") }
    }

    @Test
    fun removeChildById_throwsIfIdHasOnlySpaces() {
        val node = Node3D()
        val childNode3D = Node3D("childNode3D")
        node.addChild(childNode3D)

        assertThrows<IllegalArgumentException> { node.removeChildById("    ") }
    }

    @Test
    fun removeChildById_throwsIfIdHasSpaceAsFirstChar() {
        val node = Node3D()
        val childNode3D = Node3D("childNode3D")
        node.addChild(childNode3D)

        assertThrows<IllegalArgumentException> { node.removeChildById(" id") }
    }

    @Test
    fun removeChildAtIndex_removesCorrectChild() {
        val node = Node3D()
        val childNode3D1 = Node3D("childNode3D1")
        node.addChild(childNode3D1)
        val childNode3D2 = Node3D("childNode3D2")
        node.addChild(childNode3D2)
        val childNode3D3 = Node3D("childNode3D3")
        node.addChild(childNode3D3)
        val childNode3D4 = Node3D("childNode3D4")
        node.addChild(childNode3D4)

        node.removeChildAtIndex(2)

        assertEquals(childNode3D1, node.getChildById("childNode3D1"))
        assertEquals(childNode3D2, node.getChildById("childNode3D2"))
        assertThrows<Exception> { node.getChildById("childNode3D3") }
        assertEquals(null, childNode3D3.parent)
        assertEquals(childNode3D4, node.getChildById("childNode3D4"))
    }

    @Test
    fun removeChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = Node3D()
        val childNode3D = Node3D("childNode3D")
        node.addChild(childNode3D)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(2) }
    }

    @Test
    fun removeChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node3D()
        val childNode3D = Node3D("childNode3D")
        node.addChild(childNode3D)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(-1) }
    }

    @Test
    fun removeChildAtIndex_throwsIfThereAreNoChildren() {
        val node = Node3D()

        assertThrows<IllegalStateException> { node.removeChildAtIndex(0) }
    }
}
