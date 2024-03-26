import math.div
import nodes.nodes2d.Node2D
import nodes.nodes2d.Sprite
import nodes.nodes2d.TextLabel
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
        val testNode = Node2D(id ="empty-node", position = windowSize/2, anchorPoint = Vector2f(0.5f), scale = Vector2f(0.25f), size = Vector2f(300.0f))
        addChild(testNode)

        val sprite = Sprite(id = "dirt", anchorPoint = Vector2f(1f), filePath = "src\\test\\resources\\dirt512x.png")
        testNode.addChild(sprite)

        val textLabel = TextLabel(id = "fps-counter", position = Vector2f(0.0f, windowSize.y - 19.0f), anchorPoint = Vector2f(0.0f, 1.0f), fontSize = 40.0f, text = "Initial Text")
        addChild(textLabel)

        createResource(SecondCounter(0))

        addCodeBlock("start", CodeBlock.START) {
            shader.createUniform("test", Vector3f(1f, 1f, 1f))
            shader.createUniform("time", frames.toFloat())
            textLabel.setText("FPS: 0")
        }

        addCodeBlock("update", CodeBlock.UPDATE) {
            val lastSecond: Long = getResource<SecondCounter>().second
            val currentSecond: Long = round(System.currentTimeMillis().toDouble()/1000).toLong()

            if (currentSecond >= lastSecond + 1) {
                textLabel.setText("FPS: $frames")
                frames = 0
                setResource<SecondCounter>(SecondCounter(currentSecond))
            }
            shader.setUniform(shader.getUniform("time"), framesButBetter.toFloat())
            framesButBetter++
            frames++
        }
    }
}