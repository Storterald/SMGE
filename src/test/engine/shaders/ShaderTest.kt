package shaders

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import renderEngine.closeDisplay
import renderEngine.createDisplay

class ShaderTest {
    init {
        createDisplay("Shader Test")
    }

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test
    fun constructor_DoesNotThrowIfDisplayIsInitialized() {
        assertDoesNotThrow { Shader("src\\main\\engine\\shaders\\VertexShader.glsl", "src\\main\\engine\\shaders\\FragmentShader.glsl") }
    }

    @Test
    fun constructor_throwsIfDisplayIsNotInitialized() {
        // Kotlin runs test in parallel, so this test doesn't work even if it should
        closeDisplay()
        assertThrows<IllegalStateException> { Shader("src\\main\\engine\\shaders\\VertexShader.glsl", "src\\main\\engine\\shaders\\FragmentShader.glsl") }
    }

}