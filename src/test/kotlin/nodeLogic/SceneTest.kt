package nodeLogic

import nodeLogic.nodeLogic3d.Scene3D
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import renderEngine.createDisplay

class SceneTest {

    // ### SETTERS TEST ###
    // ---------------------

    @Test
    fun scene_doesNotChangeTheValue() {
        createDisplay("Title")
        val scene1 = Scene3D()
        val scene2 = Scene3D()
        scene1.scene = scene2

        assertEquals(null, scene1.scene)
    }

    @Test
    fun visible_doesNotChangeTheValue() {
        createDisplay("Title")
        val scene = Scene3D()
        scene.visible = false

        assertEquals(true, scene.visible)
    }

}