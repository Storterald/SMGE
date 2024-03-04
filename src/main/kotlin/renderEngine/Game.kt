package renderEngine

import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import kotlin.random.Random.Default.nextFloat

class Game {
    init {
        run()
    }

    private var window: Long = 0

    private fun run() {
        init()
        loop()

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null)?.free();

    }

    private fun init() {
        GLFWErrorCallback.createPrint(System.err).set()

        check(glfwInit()) { "Unable to initialize GLFW" }

        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

        window = glfwCreateWindow(300, 300, "Hello World!", 0, 0)
        if (window == 0L) throw RuntimeException("Failed to create the GLFW window")

        glfwSetKeyCallback(window) {
            window: Long, key: Int, scancode: Int, action: Int, mods: Int -> if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true)
        }

        stackPush().use { stack ->
            val pWidth = stack.mallocInt(1)
            val pHeight = stack.mallocInt(1)

            glfwGetWindowSize(window, pWidth, pHeight)

            val vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor())!!

            glfwSetWindowPos(
                window,
                (vidMode.width() - pWidth[0]) / 2,
                (vidMode.height() - pHeight[0]) / 2
            )
        }

        glfwMakeContextCurrent(window)

        glfwSwapInterval(1)

        glfwShowWindow(window)
    }

    private fun loop() {
        GL.createCapabilities()

        glClearColor(nextFloat(), nextFloat(), nextFloat(), 0.0f)

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

            glfwSwapBuffers(window)

            glfwPollEvents()
        }
    }
}