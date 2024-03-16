package nodeLogic.nodeLogic2d

import org.joml.Vector2f
import renderEngine.Texture
import util.FontExtra
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File

class TextLabel: Object2D {
    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), text: String, font: Font = Font("Arial", Font.PLAIN, 12)): super(id, position, anchorPoint, scale) {
        this.font = FontExtra(font.deriveFont(font.style, 12.0f))
        this.text = text

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, text: String, font: Font = Font("Arial", Font.PLAIN, 20)): super(id, position, anchorPoint, scale) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }

        this.font = FontExtra(font.deriveFont(font.style, fontSize))
        this.text = text

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), text: String, fontFile: File): this(id, position, anchorPoint, scale, text, Font.createFont(Font.TRUETYPE_FONT, fontFile)) {
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, text: String, fontFile: File): this(id, position, anchorPoint, scale, fontSize, text, Font.createFont(Font.TRUETYPE_FONT, fontFile)) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), text: String, fontPath: String): this(id, position, anchorPoint, scale, text, File(fontPath))

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, text: String, fontPath: String): this(id, position, anchorPoint, scale, fontSize, text, File(fontPath))

    var text: String
        private set

    var font: FontExtra
        private set

    override var size: Vector2f = Vector2f(0.0f)
        set(value) {}

    private fun createTexture() {
        var drawnText = ""
        for (c in text) {
            val charLabel = CharLabel(id = "$c", position = Vector2f(font.getWidth(drawnText), font.getHeight(drawnText)), anchorPoint = Vector2f(0.0f, 1.0f), fontSize = font.size2D, char = c, font = font)
            addChild(charLabel)
            drawnText += c
        }
    }

}