package nodeLogic.nodeLogic2d

import org.joml.Vector2f
import renderEngine.Texture
import util.FontExtra
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File

class TextLabel: Object2D {
    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), text: String, font: Font = Font("Arial", Font.PLAIN, 12)): super(id, position, anchorPoint, scale) {
        this.font = FontExtra(font)
        this.text = text

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, text: String, font: Font = Font("Arial", Font.PLAIN, 20)): super(id, position, anchorPoint, scale) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }

        this.font = FontExtra(font.deriveFont(font.style, fontSize))
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
        private set

    var font: FontExtra = FontExtra(Font.createFont(Font.TRUETYPE_FONT, File("src\\main\\resources\\arial.ttf")))
        private set

    var fontSize: Float = 12.0f
        set(value) {
            require(value > 0.0f) { "The font size must be higher than 0." }
            font = FontExtra(font.deriveFont(font.style, value))

            field = value
        }

    var texture: Texture = Texture()
        get() {
            field = (FontExtra::createTextureWithText)(FontExtra(font), text)

            return field
        }
        private set

}