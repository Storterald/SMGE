package scene

import shaders.Shader

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil
import org.lwjgl.glfw.Callbacks

import nodeLogic.Node
import nodeLogic.nodeLogic2d.Object2D
import nodeLogic.nodeLogic2d.Sprite
import org.joml.Vector2f
import org.joml.Vector3f
import shaders.Mesh

import windowID
import windowSize

val indices2D = intArrayOf(
    0, 1, 3,
    3, 1, 2,
)
val colors2D = arrayListOf(
    Vector3f(0.5f, 0.0f, 0.0f),
    Vector3f(0.0f, 0.5f, 0.0f),
    Vector3f(0.0f, 0.0f, 0.5f),
    Vector3f(0.0f, 0.5f, 0.5f),
)

abstract class Scene(open val shader: Shader = Shader("src\\main\\engine\\shaders\\VertexShader.glsl", "src\\main\\engine\\shaders\\FragmentShader.glsl")): Node("") {
    init {
        check(windowID != 0L) { "Initialize the display before creating a scene" }
    }

    private val meshes: HashMap<Node, Mesh> = hashMapOf()
    val nodesToRender: MutableList<Node> = mutableListOf()

    private var startCodeBlocks: HashMap<String, () -> Unit> = hashMapOf()
    private var updateCodeBlocks: HashMap<String, () -> Unit> = hashMapOf()
    private var closeCodeBlocks: HashMap<String, () -> Unit> = hashMapOf()

    override var scene: Scene? = null
        set(value) {
            field = null
        }

    override var visible: Boolean = true
        set(value) {
            field = true
        }


    fun start() {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        shader.bind()

        // Execute user code
        startCodeBlocks.forEach { it.value() }

        // Transform nodes to meshes
        nodesToRender.forEach {
            if (it is Object2D) {
                meshes[it] = Mesh(it.absolutePosition.toVerticesArray(windowSize), indices2D, colors2D)
            }
        }

        //Render meshes
        meshes.forEach {
            // TODO add check for above nodes visibility before rendering
            if (it.key.visible) it.value.renderMesh()
        }

        shader.unbind()
    }

    fun update() {
        checkForWindowResize()

        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        // Bind the shader and render the meshes
        shader.bind()

        // Execute user code
        updateCodeBlocks.forEach { it.value() }

        //Render meshes
        meshes.forEach {
            if (it.key.visible) it.value.renderMesh()
        }

        shader.unbind()

        glfwSwapBuffers(windowID)
        glfwPollEvents()
    }

    fun close() {
        Callbacks.glfwFreeCallbacks(windowID)
        glfwDestroyWindow(windowID)

        // Cleanup shader resources
        shader.cleanup()

        // Execute user code
        closeCodeBlocks.forEach { it.value() }

        glDisableVertexAttribArray(0)

        // Removes all meshes
        meshes.forEach {
            it.value.cleanUp()
        }
        meshes.clear()

        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
    }

    fun addCodeBlock(name: String, where: CodeBlock , codeBlock: () -> Unit) {
        check(!startCodeBlocks.containsKey(name)) { "There is already a code block with this name executed on start." }
        check(!updateCodeBlocks.containsKey(name)) { "There is already a code block with this name executed on update." }
        check(!closeCodeBlocks.containsKey(name)) { "There is already a code block with this name executed on close." }

        when (where) {
            CodeBlock.START -> startCodeBlocks[name] = codeBlock
            CodeBlock.UPDATE -> updateCodeBlocks[name] = codeBlock
            CodeBlock.CLOSE -> closeCodeBlocks[name] = codeBlock
        }
    }

    fun deleteCode(name: String) {
        check(startCodeBlocks.containsKey(name) || updateCodeBlocks.containsKey(name) || closeCodeBlocks.containsKey(name)) { "There is no code block with this name" }
        if (startCodeBlocks.containsKey(name)) startCodeBlocks.remove(name)
        if (updateCodeBlocks.containsKey(name)) updateCodeBlocks.remove(name)
        if (closeCodeBlocks.containsKey(name)) closeCodeBlocks.remove(name)
    }

    private var previousWindowSize = windowSize
    private fun checkForWindowResize() {
        // Window resize
        val pWidth = MemoryUtil.memAllocInt(1)
        val pHeight = MemoryUtil.memAllocInt(1)
        glfwGetWindowSize(windowID, pWidth, pHeight)

        previousWindowSize = windowSize
        windowSize = Vector2f(
            pWidth.get().toFloat(),
            pHeight.get().toFloat()
        )

        MemoryUtil.memFree(pWidth)
        MemoryUtil.memFree(pHeight)

        if (windowSize != previousWindowSize) {
            glViewport(0, 0, windowSize.x.toInt(), windowSize.y.toInt())
        }
    }
}

enum class CodeBlock {
    START, UPDATE, CLOSE
}