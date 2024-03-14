import math.div
import nodeLogic.nodeLogic2d.Object2D
import org.joml.Vector2f
import scene.Resource
import org.joml.Vector3f
import renderEngine.createResource
import renderEngine.getResource
import renderEngine.setResource
import scene.CodeBlock
import scene.Scene
import shaders.Mesh
import shaders.Shader

data class Time(var time: Long): Resource() {
    private var lastFrameTime = System.nanoTime()

    val deltaTime: Float
        get() {
            val now = System.nanoTime()
            val elapsedTime = now - lastFrameTime
            lastFrameTime = now

            return elapsedTime.toFloat() / 1000000000
        }

}
class FramesCounterScene: Scene() {
    init {
        val object2D = Object2D(position = windowSize/2, anchorPoint = Vector2f(0.5f), size = Vector2f(100.0f))
        addChild(object2D)

        createResource<Time>(Time(0))

        addCodeBlock("start", CodeBlock.START) {
            // TODO muovi questo in privateStart() che e' l'unico codice che impedisce di avere la roba iniziale completamente nell'init.
            shader.createUniform("test", Vector3f(1f, 0f, 0.5f))
        }

        addCodeBlock("update", CodeBlock.UPDATE) {
            val newTime = getResource<Time>()
            newTime.time++

            setResource<Time>(newTime)

            println("FRAMES PASSED: ${newTime.time}")
            println("DELTA TIME: ${newTime.deltaTime}")
        }
    }
}