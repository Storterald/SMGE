import math.div
import nodeLogic.nodeLogic2d.Sprite
import org.joml.Vector2f
import org.joml.Vector3f
import renderEngine.createResource
import renderEngine.getResource
import renderEngine.setResource
import scene.CodeBlock
import scene.Resource
import scene.Scene
import kotlin.math.round

data class SecondCounter(var second: Long): Resource()

class FPSCounterScene: Scene() {
    private var frames: Int = 0
    init {
        val sprite = Sprite(position = Vector2f(windowSize/2), anchorPoint = Vector2f(0.5f), filePath = "src\\test\\resources\\dirt512x.png")
        addChild(sprite)

        createResource(SecondCounter(0))

        addCodeBlock("start", CodeBlock.START) {
            // TODO muovi questo in privateStart() che e' l'unico codice che impedisce di avere la roba iniziale completamente nell'init.
            shader.createUniform("test", Vector3f(1f, 0f, 0.5f))
        }

        addCodeBlock("update", CodeBlock.UPDATE) {
            val lastSecond: Long = getResource<SecondCounter>().second
            val currentSecond: Long = round(System.currentTimeMillis().toDouble()/1000).toLong()

            if (currentSecond >= lastSecond + 1) {
                println("[DebugScene] > FPS: $frames")
                frames = 0
                setResource<SecondCounter>(SecondCounter(currentSecond))
            }

            frames++
        }
    }
}