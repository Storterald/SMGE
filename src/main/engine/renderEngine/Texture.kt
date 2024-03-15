package renderEngine

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.stb.STBImage.*
import org.lwjgl.system.MemoryStack
import java.nio.ByteBuffer

// Completely stolen from https://github.com/SilverTiger/lwjgl3-tutorial/blob/master/src/silvertiger/tutorial/lwjgl/graphic/Texture.java
class Texture {
    private var id = 0
    init {
        id = GL11.glGenTextures()
    }

    private var width = 0

    private var height = 0

    private fun uploadData(internalFormat: Int, width: Int, height: Int, format: Int, data: ByteBuffer?) {
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, GL11.GL_UNSIGNED_BYTE, data)
    }

    private fun bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id)
    }

    fun delete() {
        GL11.glDeleteTextures(id)
    }

    private fun setParameter(name: Int, value: Int) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, name, value)
    }

    fun createTexture(width: Int, height: Int, data: ByteBuffer): Texture {
        val texture = Texture()
        texture.width = width
        texture.height = height

        texture.bind()

        texture.setParameter(GL11.GL_TEXTURE_WRAP_S, GL13.GL_CLAMP_TO_BORDER)
        texture.setParameter(GL11.GL_TEXTURE_WRAP_T, GL13.GL_CLAMP_TO_BORDER)
        texture.setParameter(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST)
        texture.setParameter(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST)

        texture.uploadData(GL11.GL_RGBA8, width, height, GL11.GL_RGBA, data)

        return texture
    }

    fun loadTexture(path: String): Texture {
        var image: ByteBuffer?
        var width: Int
        var height: Int

        MemoryStack.stackPush().use { stack ->

            val w = stack.mallocInt(1)
            val h = stack.mallocInt(1)
            val comp = stack.mallocInt(1)

            stbi_set_flip_vertically_on_load(true)
            image = stbi_load(path, w, h, comp, 4)
            check(image != null) { "Failed to load a texture file!" + System.lineSeparator() + stbi_failure_reason() }

            width = w.get()
            height = h.get()
        }
        return createTexture(width, height, image!!)
    }
}