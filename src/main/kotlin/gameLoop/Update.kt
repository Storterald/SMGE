package gameLoop

import org.lwjgl.glfw.GLFW
import renderEngine.DisplayManager
import org.lwjgl.opengl.GL30.*

import WINDOW

// Code that gets executed every frame
fun DisplayManager.update() {
    glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

    /*if (RESOLUTION != PREVIOUS_RESOLUTION) {
        glViewport(0, 0, RESOLUTION.x, RESOLUTION.y)
    }*/

    // Bind the shader
    shader.bind()

    // Bind VAO
    glBindVertexArray(vaoID)
    glEnableVertexAttribArray(0)

    // Draw vertices
    glDrawArrays(GL_TRIANGLES, 0, 3)

    // Restore state
    glDisableVertexAttribArray(0)
    glBindVertexArray(0)

    shader.unbind()

    GLFW.glfwSwapBuffers(WINDOW)
    GLFW.glfwPollEvents()
}
