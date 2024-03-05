package gameLoop

import org.lwjgl.glfw.GLFW
import renderEngine.DisplayManager

import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil
import shaders.Shader

import WINDOW

var vaoID: Int = 0
var vboID: Int = 0

// Code that gets executed on the first frame
fun DisplayManager.start() {

    shader = Shader(
        "src\\main\\kotlin\\shaders\\vertexShader.glsl",
        "src\\main\\kotlin\\shaders\\fragmentShader.glsl"
    )

    val vertices = floatArrayOf(
        0.0f,  0.5f, 0f,
        -0.5f, -0.5f, 0f,
        0.5f, -0.5f, 0f
    )

    val verticesBuffer = MemoryUtil.memAllocFloat(vertices.size)
    verticesBuffer.put(vertices).flip()

    vaoID = glGenVertexArrays()
    glBindVertexArray(vaoID)

    //check(vaoID != 0) { "Failed to load the vertex array." }

    vboID = glGenBuffers()
    glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW)

    //check(vboID != 0) { "Failed to load the vertex array." }

    MemoryUtil.memFree(verticesBuffer)

    glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

    glBindBuffer(GL_ARRAY_BUFFER, 0)
    glBindVertexArray(0)

    GLFW.glfwSetKeyCallback(WINDOW) { window, key, _, action, _ ->
        if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
            GLFW.glfwSetWindowShouldClose(window, true)
    }
}