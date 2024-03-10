import nodeLogic.nodeLogic2d.Scene2D
import math.Vec2
import nodeLogic.Resource
import renderEngine.*
import java.lang.System.currentTimeMillis
import kotlin.math.round

lateinit var SCREEN_RESOLUTION: Vec2
lateinit var WINDOW_RESOLUTION: Vec2
var WINDOW: Long = 0L
const val FPS_CAP = 360

data class Time(var second: Long): Resource()

class FPSCounterScene: Scene2D() {

    private var frames: Int = 0

    override fun update() {
        super.update()

        val lastSecond: Long = getResource<Time>().second
        val currentSecond: Long = round(currentTimeMillis().toDouble()/1000).toLong()

        if (currentSecond >= lastSecond + 1) {
            println("FPS: $frames")
            frames = 0
            setResource<Time>(Time(currentSecond))
        }

        frames++
    }
}

fun main() {
    createDisplay("Title")

    createResource<Time>(Time(0))

    loadScene(FPSCounterScene())

    gameLoop(FPS_CAP) {
        update()
    }

    deleteResource<Time>()

    closeProgram()
}