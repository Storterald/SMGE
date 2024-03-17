package renderEngine

import org.lwjgl.opengl.GL30.*
import org.lwjgl.stb.STBImage.*
import org.lwjgl.system.MemoryStack
import java.io.File
import java.nio.ByteBuffer

// Completely stolen from https://github.com/SilverTiger/lwjgl3-tutorial/blob/master/src/silvertiger/tutorial/lwjgl/graphic/Texture.java
class Texture {
    private var textureID = 0

    constructor()

    constructor(imageFile: File) {
        check(imageFile.exists()) { "The provided image path does not exist." }
        initTextureWithFile(imageFile)
    }

    constructor(imagePath: String): this(File(imagePath))

    constructor(width: Int, height: Int, imageBuffer: ByteBuffer) {
        require(width >= 0) { "The texture width must be positive" }
        require(height >= 0) { "The texture height mut be positive" }
        generateTexture(width, height, imageBuffer)
    }

    private fun initTextureWithFile(imageFile: File) {
        MemoryStack.stackPush().use { stack ->
            val w = stack.mallocInt(1)
            val h = stack.mallocInt(1)
            val channels = stack.mallocInt(1)

            val imageBuffer = stbi_load(imageFile.path, w, h, channels, 4)
            check(imageBuffer != null) { "Failed to load a texture file! ${stbi_failure_reason()}" }

            val width = w.get()
            val height = h.get()

            generateTexture(width, height, imageBuffer)

            stbi_image_free(imageBuffer)
        }
    }

    private fun generateTexture(width: Int, height: Int, imageBuffer: ByteBuffer) {
        textureID = glGenTextures() // Causes crash in tests IDK why

        glBindTexture(GL_TEXTURE_2D, textureID)
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_MIRRORED_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_MIRRORED_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, imageBuffer)
        glGenerateMipmap(GL_TEXTURE_2D)
    }

    fun bind() {
        check(textureID != 0) { "The texture was not properly initialized." }
        glBindTexture(GL_TEXTURE_2D, textureID)
    }

    fun cleanup() {
        check(textureID != 0) { "The texture was not properly initialized." }
        glDeleteTextures(textureID)
    }
}