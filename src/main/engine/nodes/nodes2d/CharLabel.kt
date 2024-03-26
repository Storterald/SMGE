package nodes.nodes2d

import org.joml.Vector2f
import java.awt.Font
import java.io.File

import renderEngine.Mesh
import renderEngine.Texture
import util.FontExtra

import windowSize

private val indices2D = intArrayOf(0, 1, 3, 3, 1, 2,)
private val textureCoords2D = floatArrayOf(0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f)

class CharLabel: Object2D {
    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        char: Char,
        font: Font = Font("Arial", Font.PLAIN, 12)
    ): super(id, position, anchorPoint, scale) {
        this.font = FontExtra(font.deriveFont(font.style, 12.0f))
        this.char = char

        texture = this.font.charTextures[char] ?: throw Exception("Error obtaining texture for char '$char' with font \"${font.name}\" and size ${font.size2D}")

        this.size = Vector2f(this.font.getWidth("$char"), this.font.getHeight("$char"))
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        fontSize: Float,
        char: Char,
        font: Font = Font("Arial", Font.PLAIN, 20)
    ): super(id, position, anchorPoint, scale) {
        require(fontSize > 0.0f) { "The font size must be higher than 0." }

        this.font = FontExtra(font.deriveFont(font.style, fontSize))
        this.char = char

        texture = this.font.charTextures[char] ?: throw Exception("Error obtaining texture for char '$char' with font \"${font.name}\" and size ${font.size2D}")

        this.size = Vector2f(this.font.getWidth("$char"), this.font.getHeight("$char"))
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        char: Char,
        fontFile: File
    ): this(id, position, anchorPoint, scale, char, Font.createFont(Font.TRUETYPE_FONT, fontFile))

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        fontSize: Float,
        char: Char,
        fontFile: File
    ): this(id, position, anchorPoint, scale, fontSize, char, Font.createFont(Font.TRUETYPE_FONT, fontFile))

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        char: Char,
        fontPath: String
    ): this(id, position, anchorPoint, scale, char, File(fontPath))

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        fontSize: Float,
        char: Char,
        fontPath: String
    ): this(id, position, anchorPoint, scale, fontSize, char, File(fontPath))

    private val char: Char

    private val font: FontExtra

    private val texture: Texture

    override fun loadMesh() {
        mesh = Mesh(absolutePosition.toVerticesArray(windowSize), indices2D, texture, textureCoords2D)
    }
}