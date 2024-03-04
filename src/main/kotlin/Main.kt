import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWKeyCallbackI
import renderEngine.DisplayManager
import java.awt.SystemColor.window


lateinit var RESOLUTION: Pair<Int, Int>
var WIDTH: Int = 144
var HEIGHT: Int = 144
var WINDOW = 0L
const val FPS = 360

fun main() {
    val display = DisplayManager("titolo molto bello uwu sos")

    while (!glfwWindowShouldClose(WINDOW)) {
        display.render()
        glfwSetKeyCallback(WINDOW) {
            window, key, scancode, action, mods -> if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true)
        }
    }
    //display.currentShader.cleanup()

    //display.closeDisplay()
}