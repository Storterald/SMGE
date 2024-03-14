package shaders

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import renderEngine.createDisplay

import windowID

class ShaderTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_DoesNotThrowIfDisplayIsInitialized() {
        createDisplay("Title")
        assertDoesNotThrow { Shader("src\\main\\engine\\shaders\\VertexShader.glsl", "src\\main\\engine\\shaders\\FragmentShader.glsl") }
    }

    @Test
    fun constructor_throwsIfDisplayIsNotInitialized() {
        windowID = 0L // Thx kotlin for being bugged
        assertThrows<IllegalStateException> { Shader("src\\main\\engine\\shaders\\VertexShader.glsl", "src\\main\\engine\\shaders\\FragmentShader.glsl") }
    }

}