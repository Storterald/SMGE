package nodeLogic

import GenericScene
import nodeLogic.nodeLogic3d.Node3D
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import renderEngine.closeDisplay
import renderEngine.createDisplay

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
    fun id_doesNotThrowIfValueIsValid() {
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

    @Test
    fun visible_changesTheValueOfVisibleIfInsideScene() {
        createDisplay("Title")
        val scene = GenericScene()
        val childNode = Node3D()
        scene.addChild(childNode)

        childNode.visible = false

        assertEquals(scene, childNode.scene)
        assertEquals(false, childNode.visible)

        childNode.visible = true

        assertEquals(scene, childNode.scene)
        assertEquals(true, childNode.visible)
    }

    @Test
    fun visible_changesTheValueInsideRenderForAllBelowNodesMapIfInsideAScene() {
        createDisplay("Title")
        val scene = GenericScene()
        val childNode1 = Node3D()
        scene.addChild(childNode1)
        val childNode2 = Node3D()
        childNode1.addChild(childNode2)
        val childNode3 = Node3D()
        childNode2.addChild(childNode3)
        val childNode4 = Node3D()
        childNode2.addChild(childNode4)

        assertEquals(scene, childNode1.scene)
        assertEquals(true, childNode1.visible)
        assertEquals(scene, childNode2.scene)
        assertEquals(true, childNode2.visible)
        assertEquals(scene, childNode3.scene)
        assertEquals(true, childNode3.visible)
        assertEquals(scene, childNode4.scene)
        assertEquals(true, childNode4.visible)

        childNode2.visible = false

        assertEquals(scene, childNode1.scene)
        assertEquals(true, childNode1.visible)
        assertEquals(scene, childNode2.scene)
        assertEquals(false, childNode2.visible)
        assertEquals(scene, childNode3.scene)
        assertEquals(false, childNode3.visible)
        assertEquals(scene, childNode4.scene)
        assertEquals(false, childNode4.visible)
        closeDisplay()
    }

    // ### FUNCTIONS TEST ###
    // ---------------------

    @Test
    fun addChild_worksCorrectly() {
        val node = Node3D()
        val childNode = Node3D("childNode")
        node.addChild(childNode)

        assertEquals(1, node.getChildrenCount())
        assertEquals(childNode, node.getChildById("childNode"))
        assertEquals(node, childNode.parent)
    }

    @Test
    fun addChild_throwsIfTriedToAddItselfAsAChild() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.addChild(node) }
    }

    @Test
    fun addChild_addsChildToRenderMapIfConnectedToScene() {
        createDisplay("Title")
        val scene = GenericScene()
        val childNode0 = Node3D()
        scene.addChild(childNode0)

        val childNode1 = Node3D()
        val childNode2 = Node3D()
        childNode1.addChild(childNode2)
        val childNode3 = Node3D()
        childNode2.addChild(childNode3)
        val childNode4 = Node3D()
        childNode2.addChild(childNode4)

        childNode0.addChild(childNode1)

        assertEquals(scene, childNode1.scene)
        assertEquals(true, childNode1.visible)
        assertEquals(scene, childNode2.scene)
        assertEquals(true, childNode2.visible)
        assertEquals(scene, childNode3.scene)
        assertEquals(true, childNode3.visible)
        assertEquals(scene, childNode4.scene)
        assertEquals(true, childNode4.visible)
        closeDisplay()
    }

    @Test
    fun addChild_throwsIfTriesToAddASceneAsAChild() {
        createDisplay("Title")
        val node = Node3D()
        val scene = GenericScene()

        assertThrows<IllegalArgumentException> { node.addChild(scene) }
    }

    @Test
    fun getChildById_returnsCorrectNode3D() {
        val node = Node3D()
        val childNode = Node3D("childNode")
        node.addChild(childNode)

        assertEquals(childNode, node.getChildById("childNode"))
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
        val childNode1 = Node3D("childNode1")
        node.addChild(childNode1)
        val childNode2 = Node3D("childNode2")
        node.addChild(childNode2)
        val childNode3 = Node3D("childNode3")
        node.addChild(childNode3)
        val childNode4 = Node3D("childNode4")
        node.addChild(childNode4)

        assertEquals(childNode2, node.getChildAtIndex(1))
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = Node3D()
        val childNode1 = Node3D("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(2) }
    }

    @Test
    fun getChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node3D()
        val childNode1 = Node3D("childNode1")
        node.addChild(childNode1)

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
        val childNode1 = Node3D()
        node.addChild(childNode1)
        val childNode2 = Node3D()
        node.addChild(childNode2)
        val childNode3 = Node3D()
        node.addChild(childNode3)
        val childNode4 = Node3D()
        node.addChild(childNode4)

        assertEquals(4, node.getChildrenCount())
    }

    @Test
    fun removeChild_removesChildCorrectly() {
        val node = Node3D()
        val childNode = Node3D("childNode")
        node.addChild(childNode)

        node.removeChild(childNode)
        assertEquals(0, node.getChildrenCount())
        assertEquals(null, childNode.parent)
    }

    @Test
    fun removeChild_removesNodesFromSceneIfParentWasConnected() {
        createDisplay("Title")
        val scene = GenericScene()
        val childNode0 = Node3D()
        scene.addChild(childNode0)

        val childNode1 = Node3D()
        val childNode2 = Node3D()
        childNode1.addChild(childNode2)
        val childNode3 = Node3D()
        childNode2.addChild(childNode3)
        val childNode4 = Node3D()
        childNode2.addChild(childNode4)

        childNode0.addChild(childNode1)

        childNode0.removeChild(childNode1)

        assertEquals(null, childNode1.scene)
        assertEquals(false, scene.nodesToRender.contains(childNode1))
        assertEquals(null, childNode2.scene)
        assertEquals(false, scene.nodesToRender.contains(childNode2))
        assertEquals(null, childNode3.scene)
        assertEquals(false, scene.nodesToRender.contains(childNode3))
        assertEquals(null, childNode4.scene)
        assertEquals(false, scene.nodesToRender.contains(childNode4))
        closeDisplay()
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
        val childNode = Node3D("childNode")
        node.addChild(childNode)

        node.removeChildById("childNode")
        assertEquals(0, node.getChildrenCount())
        assertEquals(null, childNode.parent)
    }

    @Test
    fun removeChildById_throwsIfChildIsNotPresent() {
        val node = Node3D()
        assertThrows<Exception> { node.removeChildById("childNode") }
    }

    @Test
    fun removeChildById_throwsIfIdIsEmpty() {
        val node = Node3D()
        val childNode = Node3D("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("") }
    }

    @Test
    fun removeChildById_throwsIfIdHasOnlySpaces() {
        val node = Node3D()
        val childNode = Node3D("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("    ") }
    }

    @Test
    fun removeChildById_throwsIfIdHasSpaceAsFirstChar() {
        val node = Node3D()
        val childNode = Node3D("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById(" id") }
    }

    @Test
    fun removeChildAtIndex_removesCorrectChild() {
        val node = Node3D()
        val childNode1 = Node3D("childNode1")
        node.addChild(childNode1)
        val childNode2 = Node3D("childNode2")
        node.addChild(childNode2)
        val childNode3 = Node3D("childNode3")
        node.addChild(childNode3)
        val childNode4 = Node3D("childNode4")
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
        val node = Node3D()
        val childNode = Node3D("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(2) }
    }

    @Test
    fun removeChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node3D()
        val childNode = Node3D("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(-1) }
    }

    @Test
    fun removeChildAtIndex_throwsIfThereAreNoChildren() {
        val node = Node3D()

        assertThrows<IllegalStateException> { node.removeChildAtIndex(0) }
    }


    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun visible_whenConnectedToSceneChangesAllNodesBelowVisibilityToTheSameValue() {
        createDisplay("Title")
        val scene = GenericScene()
        val childNode0 = Node3D()
        scene.addChild(childNode0)
        val childNode1 = Node3D()
        childNode0.addChild(childNode1)
        val childNode2 = Node3D()
        childNode1.addChild(childNode2)
        val childNode3 = Node3D()
        childNode2.addChild(childNode3)
        val childNode4 = Node3D()
        childNode3.addChild(childNode4)

        childNode1.visible = false

        assertEquals(true, childNode0.visible)
        assertEquals(false, childNode1.visible)
        assertEquals(false, childNode2.visible)
        assertEquals(false, childNode3.visible)
        assertEquals(false, childNode4.visible)

        childNode3.visible = true

        assertEquals(true, childNode0.visible)
        assertEquals(false, childNode1.visible)
        assertEquals(false, childNode2.visible)
        assertEquals(true, childNode3.visible)
        assertEquals(true, childNode4.visible)

        closeDisplay()
    }
}
