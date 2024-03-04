package shaders

import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.opengl.GL20.*

import java.io.File

class Shader(vertexPath: String, fragmentPath: String) {
    var programId = 0
        private set
    var vertexShaderId = 0
        private set
    var fragmentShaderId = 0
        private set

    init {
        require(vertexPath != "") { "Vertex path cannot be empty." }
        require(fragmentPath != "") { "Fragment path cannot be empty." }

        println("AAAAAA")

        programId = glCreateProgram()
        check(programId != 0) { "Could not create shader program." }

        println("BBBBB")

        val vertexFile = File(vertexPath)
        val fragmentFile = File(fragmentPath)

        check(vertexFile.exists()) { "Vertex shader source file doesn't exist." }
        check(fragmentFile.exists()) { "Vertex shader source file doesn't exist." }

        println("CCCCCC")

        val vertexCode = vertexFile.readText()
        val fragmentCode = fragmentFile.readText()

        createVertexShader(vertexCode)

        println("DDDDDD")

        createFragmentShader(fragmentCode)

        println("EEEEEEE")

        link()

        println("FFFFFF")
    }

    private fun createVertexShader(shaderCode: String) {
        vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER)
    }

    private fun createFragmentShader(shaderCode: String) {
        fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER)
    }

    private fun createShader(shaderCode: String, shaderType: Int): Int {
        val shaderId = glCreateShader(shaderType)
        check(shaderId != 0) { "Error creating shader of type $shaderType." }

        glShaderSource(shaderId, shaderCode)
        glCompileShader(shaderId)

        check(glGetShaderi(shaderId, GL_COMPILE_STATUS) != 0) { "Error compiling shader ${glGetShaderInfoLog(shaderId, 1024)}." }

        glAttachShader(programId, shaderId)
        return shaderId
    }

    private fun link() {
        glLinkProgram(programId)
        check(glGetProgrami(programId, GL_LINK_STATUS) != 0) { "Error linking shader ${glGetProgramInfoLog(programId, 1024)}." }

        if (vertexShaderId != 0) {
            glDetachShader(programId, vertexShaderId)
        }
        if (fragmentShaderId != 0) {
            glDetachShader(programId, fragmentShaderId)
        }

        glValidateProgram(programId)
        check(glGetProgrami(programId, GL_VALIDATE_STATUS) != 0) { "Warning validating shader ${glGetProgramInfoLog(programId, 1024)}." }
    }

    fun bind() {
        glUseProgram(programId)
    }

    fun unbind() {
        glUseProgram(0)
    }

    fun cleanup() {
        unbind()
        if (programId != 0) {
            glDeleteProgram(programId)
        }
    }
}