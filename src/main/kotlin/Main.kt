import nodeLogic.nodeLogic2d.Scene2D
import math.Vec2
import org.lwjgl.glfw.GLFW
import renderEngine.SceneManager
import shaders.Shader
import renderEngine.createDisplay

lateinit var RESOLUTION: Vec2
var WIDTH: Float = 0.0f
var HEIGHT: Float = 0.0f
var WINDOW = 0L

fun main() {
    createDisplay("titolo")
    val shader = Shader(
        "src\\main\\kotlin\\shaders\\VertexShader.glsl",
        "src\\main\\kotlin\\shaders\\FragmentShader.glsl"
    )
    val scene = Scene2D("bob", shader = shader)
    val sceneManager = SceneManager(scene)

    while (!GLFW.glfwWindowShouldClose(WINDOW)) {
        sceneManager.update()
    }
}