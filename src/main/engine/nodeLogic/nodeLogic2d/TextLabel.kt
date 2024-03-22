package nodeLogic.nodeLogic2d

import org.joml.Vector2f
import util.FontExtra
import java.awt.*
import java.io.File

class TextLabel: Object2D {
    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        rotation: Vector2f = Vector2f(0.0f),
        text: String,
        font: Font = Font("Arial", Font.PLAIN, 12)
    ): super(id, position, anchorPoint, scale, rotation = rotation) {
        this.font = FontExtra(font.deriveFont(font.style, 12.0f))
        this.text = text

        this.scale = scale
        this.anchorPoint = anchorPoint

        createTexture()
    }

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        rotation: Vector2f = Vector2f(0.0f),
        fontSize: Float,
        text: String,
        font: Font = Font("Arial", Font.PLAIN, 20)
    ): super(id, position, anchorPoint, scale, rotation = rotation) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }

        this.font = FontExtra(font.deriveFont(font.style, fontSize))
        this.text = text

        this.scale = scale
        this.anchorPoint = anchorPoint

        createTexture()
    }

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        rotation: Vector2f = Vector2f(0.0f),
        text: String,
        fontFile: File
    ): this(id, position, anchorPoint, scale, rotation, text, Font.createFont(Font.TRUETYPE_FONT, fontFile))

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        rotation: Vector2f = Vector2f(0.0f),
        fontSize: Float,
        text: String,
        fontFile: File
    ): this(id, position, anchorPoint, scale, rotation, fontSize, text, Font.createFont(Font.TRUETYPE_FONT, fontFile))

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        rotation: Vector2f = Vector2f(0.0f),
        text: String,
        fontPath: String
    ): this(id, position, anchorPoint, scale, rotation, text, File(fontPath))

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        rotation: Vector2f = Vector2f(0.0f),
        fontSize: Float,
        text: String,
        fontPath: String
    ): this(id, position, anchorPoint, scale, rotation, fontSize, text, File(fontPath))

    var text: String
        private set

    var font: FontExtra
        private set

    fun setText(text: String) {
        removeChildrenOfType(CharLabel::class)

        this.text = text

        createTexture()
    }

    fun setFont(font: Font) {
        removeChildrenOfType(CharLabel::class)

        this.font = FontExtra(font.deriveFont(this.font.style, this.font.size2D))

        createTexture()
    }

    fun setFont(fontFile: File) {
        check(fontFile.exists()) { "The font file doesn't exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof file." }
        setFont(Font.createFont(Font.TRUETYPE_FONT, fontFile))
    }

    fun setFont(fontPath: String) {
        setFont(File(fontPath))
    }

    fun setFontSize(fontSize: Float) {
        require(fontSize > 0.0) { "The font size must be higher than 0." }
        removeChildrenOfType(CharLabel::class)

        font = FontExtra(font.deriveFont(font.style, fontSize))

        createTexture()
    }

    private fun createTexture() {
        var drawnText = ""
        for (c in text) {
            val charLabel = CharLabel(id = if (c == ' ') "" else "$c", position = Vector2f(font.getWidth(drawnText), 0.0f), anchorPoint = Vector2f(0.0f, 1.0f), fontSize = font.size2D, char = c, font = font)
            addChild(charLabel)
            drawnText += c
        }

        size = Vector2f(font.getWidth(text), font.getHeight(text))
    }

}