package shaders

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

        programId = glCreateProgram()
        check(programId != 0) { "Could not create shader program." }


        val vertexFile = File(vertexPath)
        val fragmentFile = File(fragmentPath)

        check(vertexFile.exists()) { "Vertex shader source file doesn't exist." }
        check(fragmentFile.exists()) { "Fragment shader source file doesn't exist." }


        val vertexCode = vertexFile.readText()
        val fragmentCode = fragmentFile.readText()

        createVertexShader(vertexCode)

        createFragmentShader(fragmentCode)


        link()

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