package renderEngine

import nodeLogic.nodeLogic2d.Scene2D
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import shaders.Shader

class SceneManagerTest {

    @Test
    fun constructor_createsDisplayWithoutThrowing2D() {
        val shader = Shader(
            "src\\main\\kotlin\\shaders\\VertexShader.glsl",
            "src\\main\\kotlin\\shaders\\FragmentShader.glsl"
        )
        val scene = Scene2D("bob", shader = shader)
        assertDoesNotThrow { SceneManager("Title", scene) }
    }

    @Test
    fun constructor_throwsIfNameIsEmpty2D() {
        val shader = Shader(
            "src\\main\\kotlin\\shaders\\VertexShader.glsl",
            "src\\main\\kotlin\\shaders\\FragmentShader.glsl"
        )
        val scene = Scene2D("bob", shader = shader)
        assertThrows<IllegalArgumentException> { SceneManager("", scene) }
    }

    @Test
    fun constructor_throwsIfNameIsOnlyMadeBySpaces2D() {
        val shader = Shader(
            "src\\main\\kotlin\\shaders\\VertexShader.glsl",
            "src\\main\\kotlin\\shaders\\FragmentShader.glsl"
        )
        val scene = Scene2D("bob", shader = shader)
        assertThrows<IllegalArgumentException> { SceneManager("    ", scene) }
    }

    @Test
    fun constructor_throwsIfNameHasSpaceAsFirstChar2D() {
        val shader = Shader(
            "src\\main\\kotlin\\shaders\\VertexShader.glsl",
            "src\\main\\kotlin\\shaders\\FragmentShader.glsl"
        )
        val scene = Scene2D("bob", shader = shader)
        assertThrows<IllegalArgumentException> { SceneManager(" Title", scene) }
    }

}