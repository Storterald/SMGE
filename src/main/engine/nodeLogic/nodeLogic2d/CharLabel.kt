package nodeLogic.nodeLogic2d

import org.joml.Vector2f
import renderEngine.Texture
import util.FontExtra
import java.awt.Font
import java.io.File

class CharLabel: Object2D {
    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), char: Char, font: Font = Font("Arial", Font.PLAIN, 12)): super(id, position, anchorPoint, scale) {
        this.font = FontExtra(font.deriveFont(font.style, 12.0f))
        this.char = char

        texture = Texture()

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, char: Char, font: Font = Font("Arial", Font.PLAIN, 20)): super(id, position, anchorPoint, scale) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }

        this.font = FontExtra(font.deriveFont(font.style, fontSize))
        this.char = char

        texture = Texture()

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), char: Char, fontFile: File): this(id, position, anchorPoint, scale, char, Font.createFont(Font.TRUETYPE_FONT, fontFile)) {
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, char: Char, fontFile: File): this(id, position, anchorPoint, scale, fontSize, char, Font.createFont(Font.TRUETYPE_FONT, fontFile)) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }
        check(fontFile.exists()) { "The font file does not exist." }
        require(fontFile.extension in arrayOf("otf", "ttf", "woff", "svg", "eof", "OTF", "TTF", "WOFF", "SVG", "EOF")) { "The font must be a otf, ttf, woff, svg or eof." }
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), char: Char, fontPath: String): this(id, position, anchorPoint, scale, char, File(fontPath))

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f), anchorPoint: Vector2f = Vector2f(0.5f), scale: Vector2f = Vector2f(1.0f), fontSize: Float, char: Char, fontPath: String): this(id, position, anchorPoint, scale, fontSize, char, File(fontPath))

    var char: Char
        private set

    var font: FontExtra = FontExtra(Font.createFont(Font.TRUETYPE_FONT, File("src\\main\\resources\\arial.ttf")))
        private set

    var texture: Texture
        private set
}