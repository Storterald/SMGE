package renderEngine

import HEIGHT
import RESOLUTION
import WIDTH
import WINDOW
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import shaders.Shader


class DisplayManager(private val windowTitle: String) {

    init {
        require(windowTitle != "") { "The window name must contain at least one char." }
        require(windowTitle.indexOfFirst { it in " " } != -1) { "The window name must contain at least one non ' ' char." }
        require(windowTitle[0] != ' ') { "The window mustn't have a space as it's first character." }

        createDisplay()

        RESOLUTION = Pair(glfwGetVideoMode(glfwGetPrimaryMonitor())!!.width(), glfwGetVideoMode(glfwGetPrimaryMonitor())!!.height())
        WIDTH = RESOLUTION.first
        HEIGHT = RESOLUTION.second
    }

    private fun createDisplay() {
        GLFWErrorCallback.createPrint(System.err).set()

        check(glfwInit()) { "Unable to initialize GLFW" }

        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

        // Create the window
        WINDOW = glfwCreateWindow(WIDTH, HEIGHT, windowTitle , 0, 0)
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

    fun render() {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        glfwSwapBuffers(WINDOW)

        glfwPollEvents()
    }

    fun closeDisplay() {
        glfwFreeCallbacks(WINDOW)
        glfwDestroyWindow(WINDOW)

        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
    }
}