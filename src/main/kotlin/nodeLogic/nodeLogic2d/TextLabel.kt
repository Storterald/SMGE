package nodeLogic.nodeLogic2d

import math.Vec2
import java.awt.Font
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO



class TextLabel: Object2D {
    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), text: String, font: Font = Font("Arial", Font.PLAIN, 12)): super(id, position, anchorPoint, scale) {
        this.font = font
        this.text = text

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), fontSize: Float, text: String, font: Font = Font("Arial", Font.PLAIN, 20)): super(id, position, anchorPoint, scale) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }

        this.font = font.deriveFont(font.style, fontSize)
        this.text = text
        this.fontSize = fontSize

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), text: String, fontFile: File): super(id, position, anchorPoint, scale) {
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }

        val tempFont = Font.createFont(Font.TRUETYPE_FONT, fontFile)
        this.font = tempFont.deriveFont(tempFont.style, fontSize)
        this.text = text

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), fontSize: Float, text: String, fontFile: File): super(id, position, anchorPoint, scale) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }

        val tempFont = Font.createFont(Font.TRUETYPE_FONT, fontFile)
        this.font = tempFont.deriveFont(tempFont.style, fontSize)
        this.text = text
        this.fontSize = fontSize

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), text: String, fontPath: String): super(id, position, anchorPoint, scale) {
        val fontFile = File(fontPath)
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }

        val tempFont: Font = Font.createFont(Font.TRUETYPE_FONT, fontFile)
        this.font = tempFont.deriveFont(tempFont.style, fontSize)
        this.text = text

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), fontSize: Float, text: String, fontPath: String): super(id, position, anchorPoint, scale) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }

        val fontFile = File(fontPath)
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }

        val tempFont: Font = Font.createFont(Font.TRUETYPE_FONT, fontFile)
        this.font = tempFont.deriveFont(tempFont.style, fontSize)
        this.text = text
        this.fontSize = fontSize

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    var text: String = ""
        set(value) {
            createImage(value, font)

            field = value
        }

    var font: Font = Font.createFont(Font.TRUETYPE_FONT, File("src\\main\\resources\\arial.ttf"))
        set(value) {
            createImage(text, value)

            field = value
        }

    var fontSize: Float = 12.0f
        set(value) {
            require(value > 0.0f) { "The font size must be higher than 0." }
            font = font.deriveFont(font.style, value)

            field = value
        }

    lateinit var image: BufferedImage
        private set

    private fun createImage(text: String, font: Font) {
        // Completely stolen from https://javainfinite.com/java/convert-text-to-image-using-java/
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)
        var graphics2D = image.createGraphics()
        graphics2D.font = font
        var fontMetrics = graphics2D.fontMetrics
        size = Vec2(fontMetrics.stringWidth(text).toFloat(), fontMetrics.height.toFloat())
        graphics2D.dispose()

        image = BufferedImage(if (size.x.toInt() > 0) size.x.toInt() else 1, if (size.y.toInt() > 0) size.y.toInt() else 1, BufferedImage.TYPE_INT_ARGB)
        graphics2D = image.createGraphics()
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY)
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY)
        graphics2D.font = font
        fontMetrics = graphics2D.fontMetrics
        graphics2D.drawString(text, 0, fontMetrics.ascent)
        graphics2D.dispose()

        /* Remove to save the textLabel as an image in resources/
        try {
            ImageIO.write(image, "png", File("src\\main\\resources\\${font.name}.png"))
        } catch (ex: IOException) {
            ex.printStackTrace()
        } */
    }
}