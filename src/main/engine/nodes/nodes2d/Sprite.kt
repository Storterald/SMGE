package nodes.nodes2d

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import org.joml.Vector2f

import renderEngine.Mesh
import renderEngine.Texture
import windowSize

private val indices2D = intArrayOf(0, 1, 3, 3, 1, 2,)
private val textureCoords2D = floatArrayOf(0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f)

class Sprite: Object2D {
    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        rotation: Vector2f = Vector2f(0.0f),
        file: File
    ): super(id, position, anchorPoint, scale, rotation = rotation) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = file
        texture = Texture(image)

        val imageBuffer: BufferedImage = ImageIO.read(file)
        size = Vector2f(imageBuffer.width.toFloat(), imageBuffer.height.toFloat())
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        size: Vector2f,
        rotation: Vector2f = Vector2f(0.0f),
        file: File
    ): super(id, position, anchorPoint, scale, size, rotation) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = file
        texture = Texture(image)

        this.size = size
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        rotation: Vector2f = Vector2f(0.0f),
        filePath: String
    ): this(id, position, anchorPoint, scale, rotation, File(filePath))

    constructor(
        id: String = "",
        position: Vector2f = Vector2f(0.0f),
        anchorPoint: Vector2f = Vector2f(0.5f),
        scale: Vector2f = Vector2f(1.0f),
        size: Vector2f,
        rotation: Vector2f = Vector2f(0.0f),
        filePath: String
    ): this(id, position, anchorPoint, scale, size, rotation, File(filePath))

    private var image: File

    private var texture: Texture

    fun setImage(file: File) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = file
        texture = Texture(image)

        val imageBuffer: BufferedImage = ImageIO.read(file)
        size = Vector2f(imageBuffer.width.toFloat(), imageBuffer.height.toFloat())

    }

    fun setImage(filePath: String) {
        setImage(File(filePath))
    }

    override fun loadMesh() {
        mesh = Mesh(absolutePosition.toVerticesArray(windowSize), indices2D, texture, textureCoords2D)
    }

}
