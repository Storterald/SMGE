package gameLoop

import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL30.*
import renderEngine.DisplayManager

import WINDOW

// Code that gets executed when closing
fun DisplayManager.close() {
    Callbacks.glfwFreeCallbacks(WINDOW)
    GLFW.glfwDestroyWindow(WINDOW)

    // Cleanup shader resources
    shader.cleanup()
    glDisableVertexAttribArray(0)

    // Delete the VBO
    glBindBuffer(GL_ARRAY_BUFFER, 0)
    glDeleteBuffers(vboID)

    // Delete the VAO
    glBindVertexArray(0)
    glDeleteVertexArrays(vaoID)

    GLFW.glfwTerminate()
    GLFW.glfwSetErrorCallback(null)?.free()
}