package nodeLogic.nodeLogic2d

import org.joml.Vector2f
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File

class TextLabel: Object2D {
    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), text: String, font: Font = Font("Arial", Font.PLAIN, 12)): super(id, position, anchorPoint, scale) {
        this.font = font
        this.text = text

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, text: String, font: Font = Font("Arial", Font.PLAIN, 20)): super(id, position, anchorPoint, scale) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }

        this.font = font.deriveFont(font.style, fontSize)
        this.text = text
        this.fontSize = fontSize

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), text: String, fontFile: File): this(id, position, anchorPoint, scale, text, Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.createFont(Font.TRUETYPE_FONT, fontFile).style, 12.0f)) {
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, text: String, fontFile: File): this(id, position, anchorPoint, scale, fontSize, text, Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.createFont(Font.TRUETYPE_FONT, fontFile).style, 12.0f)) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), text: String, fontPath: String): this(id, position, anchorPoint, scale, text, File(fontPath))

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, text: String, fontPath: String): this(id, position, anchorPoint, scale, fontSize, text, File(fontPath))

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
        size = Vector2f(fontMetrics.stringWidth(text).toFloat(), fontMetrics.height.toFloat())
        graphics2D.dispose()

        image = BufferedImage(if (size.x.toInt() > 0) size.x.toInt() else 1, if (size.y.toInt() > 0) size.y.toInt() else 1, BufferedImage.TYPE_INT_ARGB)
        graphics2D = image.createGraphics()
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY)
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY)
        graphics2D.font = font
        fontMetrics = graphics2D.fontMetrics
        graphics2D.drawString(text, 0, fontMetrics.ascent)
        graphics2D.dispose()

        // Remove comment to save the textLabel as an image in resources/
//        try {
//            ImageIO.write(image, "png", File("src\\main\\resources\\${font.name}.png"))
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//        }
    }

}