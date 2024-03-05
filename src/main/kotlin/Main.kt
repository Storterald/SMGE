import org.lwjgl.glfw.GLFW.*
import renderEngine.DisplayManager


lateinit var RESOLUTION: Pair<Int, Int>
var WIDTH: Int = 144
var HEIGHT: Int = 144
var WINDOW = 0L

fun main() {
    val display = DisplayManager("titolo molto bello uwu sos")

    glfwSetKeyCallback(WINDOW) {
        window, key, scancode, action, mods -> if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
            glfwSetWindowShouldClose(window, true)
    }

    while (!glfwWindowShouldClose(WINDOW)) {
        display.render()
    }
    //display.currentShader.cleanup()

    display.closeDisplay()
}