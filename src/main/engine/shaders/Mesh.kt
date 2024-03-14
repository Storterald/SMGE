package shaders

import org.joml.Vector3f
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil

class Mesh(positions: FloatArray, indices: IntArray, colors: ArrayList<Vector3f>) {
    private var vaoID = 0
    private var vboID = 0
    private var cboID = 0
    private var eboID = 0
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

        // Init CBO
        val colorsFloat = ArrayList<Float>()
        for (color in colors) {
            colorsFloat.add(color.x)
            colorsFloat.add(color.y)
            colorsFloat.add(color.z)
        }
        val colorsFloatArray = colorsFloat.toFloatArray()

        val colorBuffer = MemoryUtil.memAllocFloat(colorsFloatArray.size)
        colorBuffer.put(colorsFloatArray).flip()

        cboID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, cboID)
        glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW)
        glEnableVertexAttribArray(1)
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0)

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

    fun cleanUp() {
        glDisableVertexAttribArray(0)
        glDisableVertexAttribArray(1)

        // Delete VBO and EBO
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glDeleteBuffers(vboID)
        glDeleteBuffers(eboID)
        glDeleteBuffers(cboID)

        // Delete VAO
        glBindVertexArray(0)
        glDeleteVertexArrays(vaoID)
    }

    fun renderMesh() {
        glBindVertexArray(vaoID)

        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0)

        glDisableVertexAttribArray(0)
        glDisableVertexAttribArray(1)
        glBindVertexArray(0)
    }
}