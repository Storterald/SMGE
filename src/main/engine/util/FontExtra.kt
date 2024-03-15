package util

import org.lwjgl.system.MemoryUtil
import java.awt.*
import java.awt.image.BufferedImage
import kotlin.math.max

import renderEngine.Texture
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.nio.ByteBuffer

private val fontTextures: MutableList<FontExtra> = mutableListOf()

// Completely stolen from https://github.com/SilverTiger/lwjgl3-tutorial/wiki/Fonts
class FontExtra(font: Font) : Font(font) {
    init {
        if (font !in fontTextures) createFontTexture(font)
    }

    private val glyphs: MutableMap<Char, Glyph> = mutableMapOf()

    private val texture = Texture()

    var height: Int = 0
        private set

    fun getHeight(text: String): Int {
        var height = 0
        var lineHeight = 0
        for (c in text) {
            if (c == '\n') {
                height += lineHeight
                lineHeight = 0
                continue
            }
            if (c == '\r') {
                continue
            }
            val g = glyphs[c]
            lineHeight = max(lineHeight.toDouble(), g!!.height.toDouble()).toInt()
        }
        height += lineHeight

        return height
    }

    private fun createCharImage(font: Font, c: Char, antiAlias: Boolean): BufferedImage? {
        var image: BufferedImage = BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)
        var g: Graphics2D = image.createGraphics()
        if (antiAlias) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        }
        g.font = font
        val metrics: FontMetrics = g.fontMetrics
        g.dispose()

        val charWidth: Int = metrics.charWidth(c)
        val charHeight: Int = metrics.height

        if (charWidth == 0) {
            return null
        }

        image = BufferedImage(charWidth, charHeight, BufferedImage.TYPE_INT_ARGB)
        g = image.createGraphics()
        if (antiAlias) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        }
        g.font = font
        g.paint = Color.WHITE
        g.drawString("$c", 0, metrics.ascent)
        g.dispose()

        return image
    }

    private fun createFontTexture(font: Font): Texture {
        var imageWidth = 0
        var imageHeight = 0

        for (i in 32..255) {
            if (i == 127) continue

            val charImage: BufferedImage = createCharImage(font, i.toChar(), true) ?: continue

            imageWidth += charImage.width
            imageHeight = max(imageHeight, charImage.height)
        }

        val image: BufferedImage = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        val g: Graphics2D = image.createGraphics();

        var x = 0

        for (i in 32..255) {
            if (i == 127) continue

            val c = i.toChar()
            val charImage: BufferedImage = createCharImage(font, c, true) ?: continue

            val charWidth: Int = charImage.width
            val charHeight: Int = charImage.height

            val glyph = Glyph(charWidth, charHeight, x, image.height - charHeight, 0.0f)
            g.drawImage(charImage, x, 0, null)
            x += glyph.width
            glyphs[c] = glyph
        }

        val transform: AffineTransform = AffineTransform.getRotateInstance(1.0, -1.0)
        transform.translate(0.0, -image.height.toDouble())
        val operation: AffineTransformOp = AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)
        operation.filter(image, null)

        val width = image.width
        val height = image.height

        val pixels = IntArray(width*height)
        image.getRGB(0, 0, width, height, pixels, 0, width)

        val buffer: ByteBuffer = MemoryUtil.memAlloc(width*height*4)
        for (i in 0..<height) {
            for (j in 0..<width) {
                val pixel = pixels[i * width + j]
                buffer.put(((pixel shr 16) and 0xFF).toByte())
                buffer.put(((pixel shr 8) and 0xFF).toByte())
                buffer.put((pixel and 0xFF).toByte())
                buffer.put(((pixel shr 24) and 0xFF).toByte())
            }
        }

        buffer.flip()

        val fontTexture: Texture = (Texture::generateTexture)(Texture(), width, height, buffer)
        MemoryUtil.memFree(buffer)

        fontTextures.add(this)

        return fontTexture
    }

    fun createTextureWithText(text: String): Texture {
        return texture
    }
}