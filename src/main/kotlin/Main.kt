import gameLoop.close
import gameLoop.start
import gameLoop.update
import math.Vec2
import org.lwjgl.glfw.GLFW.*
import renderEngine.DisplayManager
import renderEngine.THREE_DIMENSIONS

lateinit var RESOLUTION: Vec2
var WIDTH: Float = 300f
var HEIGHT: Float = 300f
var WINDOW = 0L

fun main() {
    val display = DisplayManager("titolo", THREE_DIMENSIONS)
    display.start()

    while (!glfwWindowShouldClose(WINDOW)) {
        display.update()
    }

    display.close()
}