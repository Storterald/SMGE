package renderEngine

import org.joml.Vector2f
import org.lwjgl.glfw.GLFW.*
import scene.Scene
import scene.Resource
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

import screenResolution
import windowID

private var currentScene: Scene? = null
    set(value) {
        require(value != null) { "Scene cannot be null." }
        require(value != currentScene) { "This scene is already loaded." }

        field = value
    }

val resources: HashMap<Class<*>, Resource> = hashMapOf()

private fun initDisplay() {
    GLFWErrorCallback.createPrint(System.err).set()

    check(glfwInit()) { "Unable to initialize " }

    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

    screenResolution = Vector2f(
        glfwGetVideoMode(glfwGetPrimaryMonitor())!!.width().toFloat(),
        glfwGetVideoMode(glfwGetPrimaryMonitor())!!.height().toFloat()
    )
}

fun createDisplay(windowTitle: String) {
    require(windowTitle != "") { "The title must contain at least one char." }
    require(windowTitle.trim() != "") { "The title must contain at least one non ' ' char." }
    require(windowTitle[0] != ' ') { "The title mustn't have a space as it's first character." }

    initDisplay()

    // Create the window
    windowID = glfwCreateWindow((screenResolution.x / 2.0f).toInt(), (screenResolution.y / 2.0f).toInt(), windowTitle, 0, 0)
    if (windowID == 0L) throw RuntimeException("Failed to create the GLFW window.")

    glfwSetKeyCallback(windowID) { window: Long, key: Int, scancode: Int, action: Int, mods: Int ->
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, true)
    }

    glfwMakeContextCurrent(windowID)

    glfwShowWindow(windowID)

    /* https://stackoverflow.com/a/76840008
     * Sets fps to monitor hz / value
     * 1: VSync
     * 0: As fast as possible
     */
    glfwSwapInterval(0)

    GL.createCapabilities()

    GL11.glClearColor(0.0f, 1.0f, 0.0f, 1.0f)
}

fun update() {
    check(currentScene != null) { "Load a scene before calling update()" }
    currentScene!!.update()
}

fun loadScene(scene: Scene) {
    if (currentScene != null) {
        currentScene!!.close()
    }

    currentScene = scene
    currentScene!!.start()
}

fun closeDisplay() {
    if (currentScene != null) currentScene!!.close()
    glfwSetWindowShouldClose(windowID, true)
}

inline fun <reified T : Resource> createResource(value: T) {
    check(!resources.containsKey(T::class.java)) { "A resource of this type already exist" }
    resources[T::class.java] = value
}

inline fun <reified T : Resource> setResource(value: T) {
    if (!resources.containsKey(T::class.java)) createResource(value)
    else resources[T::class.java] = value
}

inline fun <reified T : Resource> getResource(): T {
    check(resources.containsKey(T::class.java)) { "Cannot get a uninitialized resource." }
    return resources.getValue(T::class.java) as T
}

inline fun <reified T : Resource> deleteResource() {
    check(resources.containsKey(T::class.java)) { "Cannot delete a uninitialized resource." }
    resources.remove(T::class.java)
}

fun deleteResources() {
    resources.clear()
}