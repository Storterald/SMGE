package nodeLogic

import GenericScene
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import renderEngine.createDisplay

class SceneTest {

    // ### SETTERS TEST ###
    // ---------------------

    @Test
    fun visible_doesNotChangeTheValue() {
        createDisplay("Title")
        val scene = GenericScene()
        scene.visible = false

        assertEquals(true, scene.visible)
    }

}