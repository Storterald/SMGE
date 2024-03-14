package shaders

import org.joml.Vector2f
import org.joml.Vector3f
import org.lwjgl.opengl.GL30.*

import java.io.File

import windowID

class Shader(vertexPath: String, fragmentPath: String) {
    var programID = 0
        private set
    private var vertexShaderId = 0
    private var fragmentShaderId = 0

    val uniforms = HashMap<String, Uniform<*>>()

    init {
        require(vertexPath != "") { "Vertex path cannot be empty." }
        require(fragmentPath != "") { "Fragment path cannot be empty." }
        check(windowID != 0L) { "Initialize the display before creating a shader" }

        programID = glCreateProgram()
        check(programID != 0) { "Could not create shader program." }

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

        glAttachShader(programID, shaderId)
        return shaderId
    }

    private fun link() {
        glLinkProgram(programID)
        check(glGetProgrami(programID, GL_LINK_STATUS) != 0) { "Error linking shader ${glGetProgramInfoLog(programID, 1024)}." }

        if (vertexShaderId != 0) {
            glDetachShader(programID, vertexShaderId)
        }
        if (fragmentShaderId != 0) {
            glDetachShader(programID, fragmentShaderId)
        }

        glValidateProgram(programID)
        check(glGetProgrami(programID, GL_VALIDATE_STATUS) != 0) { "Warning validating shader ${glGetProgramInfoLog(programID, 1024)}." }
    }

    fun bind() {
        glUseProgram(programID)
    }

    fun unbind() {
        glUseProgram(0)
    }

    fun cleanup() {
        unbind()
        if (programID != 0) {
            glDeleteProgram(programID)
        }
    }

    // Uniforms
    // --------
    fun <T> createUniform(name: String, value: T) {
        val uniformLocation = glGetUniformLocation(programID, name)
        check(!uniforms.containsKey(name)) { "A uniform with the same name \"$name\" has been already initialized." }
        check(uniformLocation >= 0) { "Error creating uniform $name with location $uniformLocation." }

        val uniform = Uniform(name, value, uniformLocation)
        uniforms[name] = uniform

        setUniform(uniform, value)
    }

    inline fun <reified T> getUniform(name: String): Uniform<T> {
        check(uniforms.containsKey(name)) { "The uniform was not properly initialized using createUniform." }
        val uniform = uniforms[name]!!
        check(uniform.value is T) { "Uniform type mismatch." }
        return uniform as Uniform<T>
    }

    fun <T> setUniform(uniform: Uniform<T>, value: T) {
        check(uniforms.containsKey(uniform.name)) { "The uniform was not properly initialized using createUniform." }

        uniform.value = value
        uniforms[uniform.name] = uniform

        when (value) {
            is Int -> glUniform1i(uniform.uniformLocation, value)
            is Float -> glUniform1f(uniform.uniformLocation, value)
            is Vector2f -> glUniform2f(uniform.uniformLocation, value.x, value.y)
            is Vector3f -> glUniform3f(uniform.uniformLocation, value.x, value.y, value.z)
            else -> throw Exception("Type not yet implemented, womp womp.")
        }
    }
}

data class Uniform<T>(val name: String, var value: T, val uniformLocation: Int)