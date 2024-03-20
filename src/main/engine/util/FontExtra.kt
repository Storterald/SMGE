package util

import org.lwjgl.system.MemoryUtil
import renderEngine.Texture
import java.awt.*
import java.awt.image.BufferedImage
import kotlin.math.max

private val fontTextures: HashMap<FontExtra, HashMap<Char, Texture>> = hashMapOf()

// Completely stolen from https://github.com/SilverTiger/lwjgl3-tutorial/wiki/Fonts
class FontExtra(private val font: Font) : Font(font) {
    private val glyphs: MutableMap<Char, Glyph> = mutableMapOf()
    var charTextures: HashMap<Char, Texture> = hashMapOf()
        private set

    init {
        if (font !in fontTextures.keys) {
            createFontTexture(font)
        } else {
            charTextures = fontTextures[font]!!
        }
    }

    fun getWidth(text: String): Float {
        if (glyphs.isEmpty()) createFontTexture(font)

        var width = 0
        var lineWidth = 0
        for (c in text) {
            if (c == '\n') {
                width = max(width.toDouble(), lineWidth.toDouble()).toInt()
                lineWidth = 0
                continue
            }
            if (c == '\r') {
                continue
            }
            val g = glyphs[c]
            lineWidth += g!!.width
        }
        width = max(width.toDouble(), lineWidth.toDouble()).toInt()
        return width.toFloat()
    }

    fun getHeight(text: String): Float {
        if (glyphs.isEmpty()) createFontTexture(font)

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

        return height.toFloat()
    }

    private fun createCharTexture(font: Font, c: Char, antiAlias: Boolean): Texture? {
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

        val width = image.width
        val height = image.height

        val glyph = Glyph(charWidth, charHeight, 0, 0, 0.0f)
        glyphs[c] = glyph

        val pixels = IntArray(width * height)
        image.getRGB(0, 0, width, height, pixels, 0, width)

        val buffer = MemoryUtil.memAlloc(width * height * 4)
        for (i in 0 until height) {
            for (j in 0 until width) {
                val pixel = pixels[i * width + j]
                buffer.put(((pixel shr 16) and 0xFF).toByte())
                buffer.put(((pixel shr  8) and 0xFF).toByte())
                buffer.put(((pixel       ) and 0xFF).toByte())
                buffer.put(((pixel shr 24) and 0xFF).toByte())
            }
        }

        buffer.flip()

        val charTexture: Texture = Texture(width, height, buffer)
        MemoryUtil.memFree(buffer)

        return charTexture
    }

    fun createFontTexture(font: Font) {
        for (i in 32..255) {
            if (i == 127) continue

            val char = i.toChar()
            val charTexture = createCharTexture(font, char, true) ?: continue

            charTextures[char] = charTexture
        }

        println("[SMGE] > Created font texture for font \"${font.name}\" with size ${font.size2D}")

        fontTextures[this] = charTextures
    }
}