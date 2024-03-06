package renderEngine

import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.system.MemoryStack

import RESOLUTION
import WINDOW
import WIDTH
import HEIGHT
import math.Vec2
import org.lwjgl.opengl.GL11.glClearColor

fun createDisplay(windowTitle: String) {
    initDisplay()

    require(windowTitle != "") { "The window name must contain at least one char." }
    require(windowTitle.trim() != "") { "The window name must contain at least one non ' ' char." }
    require(windowTitle[0] != ' ') { "The window mustn't have a space as it's first character." }

    // Create the window
    WINDOW = glfwCreateWindow(WIDTH.toInt()/2, HEIGHT.toInt()/2, windowTitle, 0, 0)
    if (WINDOW == 0L) throw RuntimeException("Failed to create the GLFW window.")

    glfwSetKeyCallback(WINDOW) { window: Long, key: Int, scancode: Int, action: Int, mods: Int ->
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, true)
    }

    MemoryStack.stackPush().use { stack ->
        val pWidth = stack.mallocInt(1)
        val pHeight = stack.mallocInt(1)

        glfwGetWindowSize(WINDOW, pWidth, pHeight)

        val vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor())

        glfwSetWindowPos(WINDOW, (vidMode!!.width() - pWidth[0]) / 2, (vidMode.height() - pHeight[0]) / 2)
    }

    glfwMakeContextCurrent(WINDOW)

    glfwShowWindow(WINDOW)

    /*
     * https://stackoverflow.com/a/76840008
     * Sets fps to monitor hz / value
     * 1: VSync
     * 0: As fast as possible
     */
    glfwSwapInterval(1)

    GL.createCapabilities()

    glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
}

private fun initDisplay() {
    GLFWErrorCallback.createPrint(System.err).set()

    check(glfwInit()) { "Unable to initialize " }

    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

    RESOLUTION = Vec2(glfwGetVideoMode(glfwGetPrimaryMonitor())!!.width().toFloat(), glfwGetVideoMode(glfwGetPrimaryMonitor())!!.height().toFloat())
    WIDTH = RESOLUTION.x
    HEIGHT = RESOLUTION.y
}