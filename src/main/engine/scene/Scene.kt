package scene

import shaders.Shader

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil
import org.lwjgl.glfw.Callbacks

import nodes.Node
import nodes.nodes2d.Node2D
import org.joml.Vector2f

import windowID
import windowSize

abstract class Scene(open val shader: Shader = Shader("src\\main\\engine\\shaders\\VertexShader.glsl", "src\\main\\engine\\shaders\\FragmentShader.glsl")): Node("") {
    init {
        check(windowID != 0L) { "Initialize the display before creating a scene" }
    }

    val nodesToRender: HashMap<Node, Boolean> = hashMapOf()

    private var startCodeBlocks: HashMap<String, () -> Unit> = hashMapOf()
    private var updateCodeBlocks: HashMap<String, () -> Unit> = hashMapOf()
    private var closeCodeBlocks: HashMap<String, () -> Unit> = hashMapOf()

    override var visible: Boolean = true
        set(value) {
            field = true
        }

    fun start() {
        checkForWindowResize()

        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        shader.bind()

        // Execute user code
        startCodeBlocks.forEach { it.value() }

        /*
        for (node in nodesToRender.keys) {
            if (node is Object2D) {
                for (componentEntry in node.components) {
                    if (componentEntry.value is CustomScript) {
                        (componentEntry.value as CustomScript).startCode.invoke()
                    }
                }
            }
        } */

        //Render meshes
        nodesToRender.forEach {
            if (it.value && it.key.isMeshInitialized()) {
                it.key.mesh.renderMesh()
            }
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

        /*
        for (node in nodesToRender.keys) {
            if (node is Object2D) {
                for (componentEntry in node.components) {
                    if (componentEntry.value is CustomScript) {
                        (componentEntry.value as CustomScript).updateCode.invoke()
                    }
                }
            }
        } */

        //Render meshes
        nodesToRender.forEach {
//            println("id: \"${it.key.id}\"\n" +
//                    "OpenGL pos: ${(it.key as Node2D).absolutePosition.toVerticesArray(windowSize).joinToString()}\n" +
//                    "pos: ${(it.key as Node2D).position.x}, ${(it.key as Node2D).position.y}\n" +
//                    "size: ${(it.key as Node2D).size.x}, ${(it.key as Node2D).size.y}\n" +
//                    "AnPs: ${(it.key as Node2D).anchoredPosition.x}, ${(it.key as Node2D).anchoredPosition.y}\n" +
//                    "AbPs: ${(it.key as Node2D).absolutePosition.x1}, ${(it.key as Node2D).absolutePosition.y1}, " +
//                    "${(it.key as Node2D).absolutePosition.x2}, ${(it.key as Node2D).absolutePosition.y2}\n"
//            )
//            println("Window size: ${windowSize.x}, ${windowSize.y}\n")
            if (it.value && it.key.isMeshInitialized()) it.key.mesh.renderMesh()
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
        nodesToRender.forEach {
            if (it.key.isMeshInitialized()) it.key.mesh.cleanUp()
        }
        nodesToRender.clear()

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