package renderEngine

import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil

class Mesh(positions: FloatArray, indices: IntArray, private var texture: Texture, texCoords: FloatArray) {
    private var vaoID = 0
    private var vboID = 0
    private var eboID = 0

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

    fun loadTexture(texture: Texture) {
        this.texture = texture
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

        texture.cleanup()
    }

    fun renderMesh() {
        texture.bind()
        glBindVertexArray(vaoID)

        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0)

        glDisableVertexAttribArray(0)
        glDisableVertexAttribArray(1)
        glBindVertexArray(0)
    }
}