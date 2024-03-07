package nodeLogic

import nodeLogic.nodeLogic3d.Node3D
import nodeLogic.nodeLogic3d.Scene3D
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import renderEngine.createDisplay

class SceneTest {

    // ### FUNCTIONS TEST ###
    // ---------------------

    @Test
    fun addChild_addsChildToMapAndSetsValueToTrue() {
        createDisplay("Title")
        val scene = Scene3D()
        val childNode = Node3D()
        scene.addChild(childNode)

        assertEquals(scene, childNode.scene)
        assertEquals(true, scene.r[childNode])
    }

    @Test
    fun removeChild_removesNodeFromMap() {
        createDisplay("Title")
        val scene = Scene3D()
        val childNode = Node3D()
        scene.addChild(childNode)

        scene.removeChild(childNode)
        assertEquals(null, childNode.scene)
        assertEquals(null, scene.r[childNode])
    }

    @Test
    fun removeChildById_removesNodeFromMap() {
        createDisplay("Title")
        val scene = Scene3D()
        val childNode = Node3D("childNode")
        scene.addChild(childNode)

        scene.removeChildById("childNode")
        assertEquals(null, childNode.scene)
        assertEquals(null, scene.r[childNode])
    }

    @Test
    fun removeChildAtIndex_removesNodeFromMap() {
        createDisplay("Title")
        val scene = Scene3D()
        val childNode = Node3D("childNode")
        scene.addChild(childNode)

        scene.removeChildAtIndex(0)
        assertEquals(null, childNode.scene)
        assertEquals(null, scene.r[childNode])
    }

    @Test
    fun removeAllChildren_removesNodesFromMap() {
        createDisplay("Title")
        val scene = Scene3D()
        val childNode1 = Node3D()
        scene.addChild(childNode1)
        val childNode2 = Node3D()
        scene.addChild(childNode2)

        scene.removeAllChildren()
        assertEquals(null, childNode1.scene)
        assertEquals(null, scene.r[childNode1])
        assertEquals(null, childNode2.scene)
        assertEquals(null, scene.r[childNode2])
    }

}