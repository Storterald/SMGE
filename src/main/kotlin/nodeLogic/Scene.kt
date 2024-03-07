package nodeLogic

import shaders.Shader

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil

import WINDOW
import org.lwjgl.glfw.Callbacks

abstract class Scene(initialId: String = "", open val shader: Shader): Node(initialId) {
    private var vaoID: Int = 0
    private var vboID: Int = 0

    open fun start() {
        val vertices = floatArrayOf(
            0.0f,  0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f
        )

        // Create vertices buffer
        val verticesBuffer = MemoryUtil.memAllocFloat(vertices.size)
        verticesBuffer.put(vertices).flip()

        // Set the vao
        vaoID = glGenVertexArrays()
        glBindVertexArray(vaoID)

        // Set the vbo
        vboID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vboID)
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW)

        // Free the memory buffer!!!
        MemoryUtil.memFree(verticesBuffer)

        // Send vertices to shader
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glBindVertexArray(0)
    }

    open fun update() {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        /*if (RESOLUTION != PREVIOUS_RESOLUTION) {
            glViewport(0, 0, RESOLUTION.x, RESOLUTION.y)
        }*/

        // Bind the shader
        shader.bind()

        // Bind VAO
        glBindVertexArray(vaoID)
        glEnableVertexAttribArray(0)

        // Draw vertices
        glDrawArrays(GL_TRIANGLES, 0, 3) // !!!!!

        // Restore state
        glDisableVertexAttribArray(0)
        glBindVertexArray(0)

        shader.unbind()

        glfwSwapBuffers(WINDOW)
        glfwPollEvents()
    }

    open fun close() {
        Callbacks.glfwFreeCallbacks(WINDOW)
        glfwDestroyWindow(WINDOW)

        // Cleanup shader resources
        shader.cleanup()
        glDisableVertexAttribArray(0)

        // Delete the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glDeleteBuffers(vboID)

        // Delete the VAO
        glBindVertexArray(0)
        glDeleteVertexArrays(vaoID)

        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
    }

    override var scene: Scene? = null
        set(value) {}

    override var visible: Boolean = true
        set(value) {}

    var r: HashMap<Node, Boolean> = hashMapOf()

    override fun addChild(node: Node) {
        super.addChild(node)
        node.scene = this
        node.loadOnScene()
    }

    override fun removeChild(node: Node) {
        node.unloadFromScene()
        super.removeChild(node)
    }

    override fun removeChildAtIndex(i: Int) {
        children[i].unloadFromScene()
        super.removeChildAtIndex(i)
    }

    override fun removeChildById(id: String) {
        children.forEach { if (it.id == id) it.unloadFromScene() }
        super.removeChildById(id)
    }

    override fun removeAllChildren() {
        children.forEach { it.unloadFromScene() }
        super.removeAllChildren()
    }
}