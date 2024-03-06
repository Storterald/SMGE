package renderEngine

import org.lwjgl.glfw.GLFW.*

import nodeLogic.Scene
import WINDOW

class SceneManager(private var currentScene: Scene) {
    init {
        currentScene.start()
    }

    fun update() {
        currentScene.update()
    }

    fun loadScene(scene: Scene) {
        currentScene.close()

        currentScene = scene
        currentScene.start()
    }

    fun closeProgram() {
        currentScene.close()
        glfwSetWindowShouldClose(WINDOW, true)
    }
}