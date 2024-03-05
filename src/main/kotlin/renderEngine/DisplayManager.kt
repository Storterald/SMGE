package renderEngine

import logic.Node
import logic.logic2d.Scene2D
import logic.logic3d.Scene3D
import math.Vec2
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import shaders.Shader

import HEIGHT
import RESOLUTION
import WIDTH
import WINDOW

const val TWO_DIMENSIONS = 0
const val THREE_DIMENSIONS = 1

class DisplayManager(private val windowTitle: String, type: Int) {
    lateinit var shader: Shader
    var scene: Node

    init {
        require(windowTitle != "") { "The window name must contain at least one char." }
        require(windowTitle.trim() != "") { "The window name must contain at least one non ' ' char." }
        require(windowTitle[0] != ' ') { "The window mustn't have a space as it's first character." }

        require(type in 0..1) { "The type must be 'TWO_DIMENSIONS'(0) or 'THREE_DIMENSIONS' (1)." }

        initDisplay()

        RESOLUTION = Vec2(glfwGetVideoMode(glfwGetPrimaryMonitor())!!.width().toFloat(), glfwGetVideoMode(glfwGetPrimaryMonitor())!!.height().toFloat())
        WIDTH = RESOLUTION.x
        HEIGHT = RESOLUTION.y

        createDisplay()

        scene = if (type == 0) Scene2D("Main-Scene") else Scene3D("Main-Scene")
    }

    private fun initDisplay() {
        GLFWErrorCallback.createPrint(System.err).set()

        check(glfwInit()) { "Unable to initialize GLFW." }

        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
    }

    private fun createDisplay() {
        // Create the window
        WINDOW = glfwCreateWindow(WIDTH.toInt(), HEIGHT.toInt(), windowTitle , 0, 0)
        if (WINDOW == 0L) throw RuntimeException("Failed to create the GLFW window")

        glfwSetKeyCallback(WINDOW) {
            window: Long, key: Int, scancode: Int, action: Int, mods: Int ->
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, true)
        }

        stackPush().use { stack ->
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

        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
}