package shaders

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import renderEngine.createDisplay

import WINDOW

class ShaderTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_DoesNotThrowIfDisplayIsInitialized() {
        createDisplay("Title")
        assertDoesNotThrow { Shader("src\\main\\kotlin\\shaders\\VertexShader.glsl", "src\\main\\kotlin\\shaders\\FragmentShader.glsl") }
    }

    @Test
    fun constructor_throwsIfDisplayIsNotInitialized() {
        WINDOW = 0L // Thx kotlin for being bugged
        assertThrows<IllegalStateException> { Shader("src\\main\\kotlin\\shaders\\VertexShader.glsl", "src\\main\\kotlin\\shaders\\FragmentShader.glsl") }
    }

}