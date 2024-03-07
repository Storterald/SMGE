import nodeLogic.nodeLogic2d.Scene2D
import math.Vec2
import nodeLogic.Resource
import org.lwjgl.glfw.GLFW
import renderEngine.SceneManager
import renderEngine.createDisplay

lateinit var SCREEN_RESOLUTION: Vec2
lateinit var WINDOW_RESOLUTION: Vec2
var WINDOW = 0L

data class Timer(var time: Int): Resource()
data class DeltaTime(var lastFrameTime: Float, var deltaTime: Float): Resource()

class TimerScene: Scene2D() {
    override fun start() {
        super.start()
        setResource(Timer(0))
    }

    override fun update() {
        super.update()
        val time = getResource<Timer>().time
        println(time)
        setResource<Timer>(Timer(time+1))
    }
}

fun main() {
    createDisplay("Title")
    val timerScene = TimerScene()
    val sceneManager = SceneManager(timerScene)

    while (!GLFW.glfwWindowShouldClose(WINDOW)) {
        sceneManager.update()
    }

    sceneManager.closeProgram()
}