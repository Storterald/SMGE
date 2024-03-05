package renderEngine

import WINDOW
import org.lwjgl.glfw.GLFW

fun main() {
    val display = DisplayManager("Title")

    GLFW.glfwSetKeyCallback(WINDOW) {
        window, key, scancode, action, mods -> if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
            GLFW.glfwSetWindowShouldClose(window, true)
    }

    while (!GLFW.glfwWindowShouldClose(WINDOW)) {
        display.render()
    }

    display.closeDisplay()
}