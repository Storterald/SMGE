package nodeLogic

import GenericScene
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import renderEngine.createDisplay

class SceneTest {

    // ### SETTERS TEST ###
    // ---------------------

    @Test
    fun scene_doesNotChangeTheValue() {
        createDisplay("Title")
        val scene1 = GenericScene()
        val scene2 = GenericScene()
        scene1.scene = scene2

        assertEquals(null, scene1.scene)
    }

    @Test
    fun visible_doesNotChangeTheValue() {
        createDisplay("Title")
        val scene = GenericScene()
        scene.visible = false

        assertEquals(true, scene.visible)
    }

}