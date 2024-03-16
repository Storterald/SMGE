import math.div
import nodeLogic.nodeLogic2d.Node2D
import nodeLogic.nodeLogic2d.Sprite
import nodeLogic.nodeLogic2d.TextLabel
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
    private var framesButBetter = 0
    init {
        val testNode = Node2D(position = Vector2f(100.0f), anchorPoint = Vector2f(0.3f, 0.25f), scale = Vector2f(0.75f))
        addChild(testNode)

        val sprite = Sprite(position = windowSize/2, anchorPoint = Vector2f(0.0f), filePath = "src\\test\\resources\\dirt512x.png")
        testNode.addChild(sprite)

        val textLabel = TextLabel(anchorPoint = Vector2f(0.0f), fontSize = 25.0f, text = "Test", fontPath = "src\\test\\resources\\testFont.otf")
        addChild(textLabel)

        createResource(SecondCounter(0))

        addCodeBlock("start", CodeBlock.START) {
            shader.createUniform("test", Vector3f(1f, 1f, 1f))
            shader.createUniform("time", frames.toFloat())
        }

        addCodeBlock("update", CodeBlock.UPDATE) {
            val lastSecond: Long = getResource<SecondCounter>().second
            val currentSecond: Long = round(System.currentTimeMillis().toDouble()/1000).toLong()

            if (currentSecond >= lastSecond + 1) {
                println("[DebugScene] > FPS: $frames")
                frames = 0
                setResource<SecondCounter>(SecondCounter(currentSecond))
            }
            shader.setUniform(shader.getUniform("time"), framesButBetter.toFloat())
            framesButBetter++
            frames++
        }
    }
}