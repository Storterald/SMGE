package renderEngine

import org.lwjgl.glfw.GLFW.*

import nodeLogic.Scene
import nodeLogic.Node
import WINDOW
import nodeLogic.Resource

class SceneManager(private var currentScene: Scene): Node("") {

    val resources = hashMapOf<Class<*>, Resource>()

    init {
        addChild(currentScene)
        currentScene.start()
    }

    fun update() {
        currentScene.update()
    }

    private fun loadScene(scene: Scene) {
        removeChild(currentScene)
        currentScene.close()

        currentScene = scene

        addChild(scene)

        currentScene.start()
    }

    fun closeProgram() {
        currentScene.close()
        glfwSetWindowShouldClose(WINDOW, true)
    }

    override var visible: Boolean = true
        set(value) {
            field = true
        }

    override var scene: Scene? = null
        set(value) {
            field = null
        }

    inline fun <reified T: Resource> setResource(toAdd: T) {
        resources[T::class.java] = toAdd
    }

    inline fun <reified T: Resource> getResource(): T {
        val result = resources[T::class.java]
        check(result != null) { "Cannot read a uninitialized resource." }
        return result as T
    }
}