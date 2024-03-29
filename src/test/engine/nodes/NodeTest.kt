package nodes

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import nodes.nodes2d.CharLabel
import nodes.nodes2d.Entity2D
import nodes.nodes2d.TextLabel
import renderEngine.createDisplay
import GenericScene

class NodeTest {
    init {
        createDisplay("Node test")
    }

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test fun constructor_doesNotThrowByDefault() {
        assertDoesNotThrow { Node() }
    }

    @Test fun constructor_doesNotThrowIfiIdIsValid() {
        assertDoesNotThrow { Node("id") }
    }

    @Test fun constructor_throwsIfIdIsOnlyMadeBySpaces() {
        assertThrows<IllegalArgumentException> { Node("    ") }
    }

    @Test fun constructor_throwsIfIdHasASpaceAsFirstChar() {
        assertThrows<IllegalArgumentException> { Node(" id") }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test fun id_doesNotThrowIfValueIsValid() {
        val node = Node()
        assertDoesNotThrow { node.id = "id" }
    }

    @Test fun id_throwsIfEmpty() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.id = "" }
    }

    @Test fun id_throwsIfOnlyMadeBySpaces() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.id = "    " }
    }

    @Test fun id_throwsIfHasASpaceAsFirstChar() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.id = " id" }
    }

    @Test fun visible_changesTheValueOfVisible() {
        val scene = GenericScene()
        val childNode = Node()
        scene.addChild(childNode)

        childNode.visible = false

        assertEquals(false, childNode.visible)

        childNode.visible = true

        assertEquals(true, childNode.visible)
    }

    @Test fun visible_changesRenderForAllBelowNodes() {
        val scene = GenericScene()
        val childNode1 = Node()
        scene.addChild(childNode1)
        val childNode2 = Node()
        childNode1.addChild(childNode2)
        val childNode3 = Node()
        childNode2.addChild(childNode3)
        val childNode4 = Node()
        childNode2.addChild(childNode4)

        childNode2.visible = false

        assertEquals(true, childNode1.visible)
        assertEquals(false, childNode2.visible)
        assertEquals(true, childNode3.visible)
        assertEquals(true, childNode4.visible)
    }

    @Test fun visible_doesNotChangeRenderToTrueIfOneNodeAboveIsNotRendered() {
        val scene = GenericScene()
        val childNode1 = Node()
        scene.addChild(childNode1)
        val childNode2 = Node()
        childNode1.addChild(childNode2)
        val childNode3 = Node()
        childNode2.addChild(childNode3)
        val childNode4 = Node()
        childNode3.addChild(childNode4)

        childNode2.visible = false

        assertEquals(true, scene.nodesToRender[childNode1])
        assertEquals(false, scene.nodesToRender[childNode2])
        assertEquals(false, scene.nodesToRender[childNode3])
        assertEquals(false, scene.nodesToRender[childNode4])

        childNode4.visible = true

        assertEquals(true, scene.nodesToRender[childNode1])
        assertEquals(false, scene.nodesToRender[childNode2])
        assertEquals(false, scene.nodesToRender[childNode3])
        assertEquals(false, scene.nodesToRender[childNode4])
    }

    @Test fun visible_changesRenderToValueIfParentIsRendered() {
        val scene = GenericScene()
        val childNode1 = Node()
        scene.addChild(childNode1)
        val childNode2 = Node()
        childNode1.addChild(childNode2)
        val childNode3 = Node()
        childNode2.addChild(childNode3)
        val childNode4 = Node()
        childNode3.addChild(childNode4)

        childNode2.visible = false

        assertEquals(true, scene.nodesToRender[childNode1])
        assertEquals(false, scene.nodesToRender[childNode2])
        assertEquals(false, scene.nodesToRender[childNode3])
        assertEquals(false, scene.nodesToRender[childNode4])

        childNode2.visible = true

        assertEquals(true, scene.nodesToRender[childNode1])
        assertEquals(true, scene.nodesToRender[childNode2])
        assertEquals(true, scene.nodesToRender[childNode3])
        assertEquals(true, scene.nodesToRender[childNode4])
    }

    @Test fun scene_changesSceneForAllNodesBelow() {
        val scene = GenericScene()
        val childNode1 = Node()
        val childNode2 = Node()
        childNode1.addChild(childNode2)

        scene.addChild(childNode1)

        assertEquals(scene, childNode1.scene)
        assertEquals(scene, childNode2.scene)

        scene.removeChild(childNode1)

        assertEquals(null, childNode1.scene)
        assertEquals(null, childNode2.scene)
    }

    // ### FUNCTIONS TEST ###
    // ---------------------

    @Test fun getType_returnsCorrectType() {
        val textLabel = TextLabel(text = "test")
        assertEquals(TextLabel::class, textLabel.getType())
    }

    @Test fun addChild_worksCorrectly() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertEquals(1, node.getChildrenCount())
        assertEquals(childNode, node.getChildById("childNode"))
        assertEquals(node, childNode.parent)
    }

    @Test fun addChild_throwsIfTriedToAddItselfAsAChild() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.addChild(node) }
    }

    @Test fun addChild_addsChildToRenderMapIfConnectedToScene() {
        val scene = GenericScene()
        val childNode0 = Node()
        scene.addChild(childNode0)

        val childNode1 = Node()
        childNode0.addChild(childNode1)
        val childNode2 = Node()
        childNode1.addChild(childNode2)
        val childNode3 = Node()
        childNode2.addChild(childNode3)
        val childNode4 = Node()
        childNode2.addChild(childNode4)

        assertEquals(scene, childNode1.scene)
        assertEquals(true, scene.nodesToRender[childNode1])
        assertEquals(scene, childNode2.scene)
        assertEquals(true, scene.nodesToRender[childNode2])
        assertEquals(scene, childNode3.scene)
        assertEquals(true, scene.nodesToRender[childNode3])
        assertEquals(scene, childNode4.scene)
        assertEquals(true, scene.nodesToRender[childNode4])
    }

    @Test fun addChild_throwsIfTriesToAddASceneAsAChild() {
        val node = Node()
        val scene = GenericScene()

        assertThrows<IllegalArgumentException> { node.addChild(scene) }
    }

    @Test fun getChildById_returnsCorrectNode() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertEquals(childNode, node.getChildById("childNode"))
    }

    @Test fun getChildById_throwsIfIdIsEmpty() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.getChildById("") }
    }

    @Test fun getChildById_throwsIfIdIsOnlyMadeBySpaces() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.getChildById("    ") }
    }

    @Test fun getChildById_throwsIfIdHasASpaceAsFirstChar() {
        val node = Node()
        assertThrows<IllegalArgumentException> { node.getChildById(" id") }
    }

    @Test fun getChildById_throwsIfNoChildIsFound() {
        val node = Node()
        assertThrows<Exception> { node.getChildById("id") }
    }

    @Test fun getChildAtIndex_removesCorrectChild() {
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

    @Test fun getChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = Node()
        val childNode1 = Node("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(2) }
    }

    @Test fun getChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node()
        val childNode1 = Node("childNode1")
        node.addChild(childNode1)

        assertThrows<IllegalArgumentException> { node.getChildAtIndex(-1) }
    }

    @Test fun getChildAtIndex_throwsIfThereAreNoChildren() {
        val node = Node()

        assertThrows<IllegalStateException> { node.getChildAtIndex(0) }
    }

    @Test fun getChildrenCount_returnsCorrectChildrenCount() {
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

    @Test fun getChildrenOfType_returnsCorrectChildren() {
        val node = Node()
        val childNode1 = TextLabel("childNode1", text = "")
        node.addChild(childNode1)
        val childNode2 = CharLabel("childNode2", char = ' ')
        node.addChild(childNode2)
        val childNode3 = TextLabel("childNode3", text = "")
        node.addChild(childNode3)
        val childNode4 = Entity2D("childNode4")
        node.addChild(childNode4)

        val children = node.getChildrenOfType(TextLabel::class)

        assertEquals(2, children.size)
        assertEquals(true, children[0] is TextLabel)
        assertEquals(true, children[1] is TextLabel)

        children.sortedBy { it.id }

        assertEquals(childNode1, children[0])
        assertEquals(childNode3, children[1])
    }

    @Test fun removeChild_removesChildCorrectly() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        node.removeChild(childNode)
        assertEquals(0, node.getChildrenCount())
        assertEquals(null, childNode.parent)
    }

    @Test fun removeChild_removesNodesFromSceneIfParentWasConnected() {
        val scene = GenericScene()
        val childNode0 = Node()
        scene.addChild(childNode0)

        val childNode1 = Node()
        childNode0.addChild(childNode1)
        val childNode2 = Node()
        childNode1.addChild(childNode2)
        val childNode3 = Node()
        childNode2.addChild(childNode3)
        val childNode4 = Node()
        childNode2.addChild(childNode4)

        childNode0.removeChild(childNode1)

        assertEquals(null, childNode1.scene)
        assertEquals(false, scene.nodesToRender.contains(childNode1))
        assertEquals(null, childNode2.scene)
        assertEquals(false, scene.nodesToRender.contains(childNode2))
        assertEquals(null, childNode3.scene)
        assertEquals(false, scene.nodesToRender.contains(childNode3))
        assertEquals(null, childNode4.scene)
        assertEquals(false, scene.nodesToRender.contains(childNode4))
    }

    @Test fun removeChild_throwsIfChildIsNotPresent() {
        val node1 = Node()
        val node2 = Node()

        assertThrows<IllegalStateException> { node1.removeChild(node2) }
    }

    @Test fun removeChildById_removesChildCorrectly() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        node.removeChildById("childNode")
        assertEquals(0, node.getChildrenCount())
        assertEquals(null, childNode.parent)
    }

    @Test fun removeChildById_throwsIfChildIsNotPresent() {
        val node = Node()
        assertThrows<IllegalStateException> { node.removeChildById("childNode") }
    }

    @Test fun removeChildById_throwsIfIdIsEmpty() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("") }
    }

    @Test fun removeChildById_throwsIfIdHasOnlySpaces() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById("    ") }
    }

    @Test fun removeChildById_throwsIfIdHasSpaceAsFirstChar() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildById(" id") }
    }

    @Test fun removeChildAtIndex_removesCorrectChild() {
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
        assertThrows<IllegalStateException> { node.getChildById("childNode3") }
        assertEquals(null, childNode3.parent)
        assertEquals(childNode4, node.getChildById("childNode4"))
    }

    @Test fun removeChildAtIndex_throwsIfIndexIsOutOfBounds() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(2) }
    }

    @Test fun removeChildAtIndex_throwsIfIndexIsNegative() {
        val node = Node()
        val childNode = Node("childNode")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(-1) }
    }

    @Test fun removeChildAtIndex_throwsIfThereAreNoChildren() {
        val node = Node()

        assertThrows<IllegalArgumentException> { node.removeChildAtIndex(0) }
    }

    @Test fun removeAllChildren_removesAllChildren() {
        val node = Node()
        val childNode1 = Node("childNode1")
        node.addChild(childNode1)
        val childNode2 = Node("childNode2")
        node.addChild(childNode2)
        val childNode3 = Node("childNode3")
        node.addChild(childNode3)
        val childNode4 = Node("childNode4")
        node.addChild(childNode4)

        node.removeAllChildren()

        assertEquals(0, node.getChildrenCount())

        assertEquals(null, childNode1.parent)
        assertEquals(null, childNode2.parent)
        assertEquals(null, childNode3.parent)
        assertEquals(null, childNode4.parent)
    }

    @Test fun removeChildrenOfType_removesCorrectChildren() {
        val node = Node()
        val childNode1 = TextLabel("childNode1", text = "")
        node.addChild(childNode1)
        val childNode2 = CharLabel("childNode2", char = ' ')
        node.addChild(childNode2)
        val childNode3 = TextLabel("childNode3", text = "")
        node.addChild(childNode3)
        val childNode4 = Entity2D("childNode4")
        node.addChild(childNode4)

        node.removeChildrenOfType(TextLabel::class)

        assertEquals(2, node.getChildrenCount())
        assertThrows<IllegalStateException> { node.getChildById("childNode1") }
        assertEquals(null, childNode1.parent)
        assertEquals(childNode2, node.getChildById("childNode2"))
        assertThrows<IllegalStateException> { node.getChildById("childNode3") }
        assertEquals(null, childNode3.parent)
        assertEquals(childNode4, node.getChildById("childNode4"))
    }

    @Test fun removeChildrenOfType_throwsIfTypeIsNotANode() {
        val node = Node()
        val childNode = TextLabel("childNode", text = "")
        node.addChild(childNode)

        assertThrows<IllegalArgumentException> { node.removeChildrenOfType(String::class) }
    }
}
