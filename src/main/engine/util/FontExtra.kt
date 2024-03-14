package util

import org.joml.Vector2f
import org.joml.Vector2i
import java.awt.*
import java.awt.image.BufferedImage
import kotlin.math.max

class FontExtra(val font: Font): Font(font) {

    private val glyphs: MutableMap<Char, Glyph> = mutableMapOf()

    fun createFontTexture() {
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
    }

    fun createCharImage(font: Font, c: Char, antiAlias: Boolean): BufferedImage? {
        // Stolen from https://github.com/SilverTiger/lwjgl3-tutorial/blob/master/src/silvertiger/tutorial/lwjgl/text/Font.java#L265
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
}