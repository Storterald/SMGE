package nodeLogic.nodeLogic2d

import nodeLogic.Scene
import shaders.Shader

open class Scene2D(initialId: String = "", override val shader: Shader = Shader("src\\main\\kotlin\\shaders\\VertexShader.glsl", "src\\main\\kotlin\\shaders\\FragmentShader.glsl")): Scene(initialId, shader) {
    override fun start() {
        super.start()
    }

    override fun update() {
        super.update()
    }

    override fun close() {
        super.close()
    }
}