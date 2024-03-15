package shaders

import org.joml.Vector3f
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL30.*
import org.lwjgl.stb.STBImage
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import renderEngine.Texture
import java.io.File
import java.nio.ByteBuffer
import java.nio.IntBuffer

class Mesh(positions: FloatArray, indices: IntArray, texturePath: String, texCoords: FloatArray) {
    private var vaoID = 0
    private var vboID = 0
    private var eboID = 0

    private var textureID = 0
    private var textureVboID = 0

    private var vertexCount = indices.size

    init {
        // Init VAO
        vaoID = glGenVertexArrays()
        glBindVertexArray(vaoID)

        // Init VBO
        val verticesBuffer = MemoryUtil.memAllocFloat(positions.size)
        vertexCount = indices.size
        verticesBuffer.put(positions).flip()

        vboID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vboID)
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW)
        glEnableVertexAttribArray(0)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

        // Init Texture VBO
        textureVboID = glGenBuffers()
        val texCoordsBuffer = MemoryUtil.memAllocFloat(texCoords.size)
        texCoordsBuffer.put(texCoords).flip()

        glBindBuffer(GL_ARRAY_BUFFER, textureVboID)
        glBufferData(GL_ARRAY_BUFFER, texCoordsBuffer, GL_STATIC_DRAW)
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0)

        // Init EBO
        val indicesBuffer = MemoryUtil.memAllocInt(indices.size)
        indicesBuffer.put(indices).flip()

        eboID = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW)

        glBindVertexArray(0)
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)

        MemoryUtil.memFree(verticesBuffer)
        MemoryUtil.memFree(indicesBuffer)
    }

    fun loadTexture(texturePath: String) {
        check(File(texturePath).exists()) { "The specified texture file does not exist." }

        var width = 0
        var height = 0
        var buffer: ByteBuffer?

        MemoryStack.stackPush().use {
            val w: IntBuffer = it.mallocInt(1)
            val h: IntBuffer = it.mallocInt(1)
            val c: IntBuffer = it.mallocInt(1)

//            STBImage.stbi_set_flip_vertically_on_load(true)
            buffer = STBImage.stbi_load(texturePath, w, h, c, 4)
            check(buffer != null) { "Failed to load image: ${STBImage.stbi_failure_reason()}." }

            width = w.get()
            height = h.get()
        }

        textureID = glGenTextures()
        glBindTexture(GL_TEXTURE_2D, textureID)

        glGenerateMipmap(GL_TEXTURE_2D)

        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer)

        STBImage.stbi_image_free(buffer!!)
    }

    fun cleanUp() {
        glDisableVertexAttribArray(0)
        glDisableVertexAttribArray(1)

        // Delete VBO and EBO
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glDeleteBuffers(vboID)
        glDeleteBuffers(eboID)

        // Delete VAO
        glBindVertexArray(0)
        glDeleteVertexArrays(vaoID)
    }

    fun renderMesh() {
        glBindVertexArray(vaoID)
        glActiveTexture(GL_TEXTURE0)
        glBindTexture(GL_TEXTURE_2D, textureID)

        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0)

        glDisableVertexAttribArray(0)
        glDisableVertexAttribArray(1)
        glBindVertexArray(0)
    }
}