package nodeLogic.nodeLogic2d

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import org.joml.Vector2f

class Sprite: Object2D {
    constructor(id: String = "", position: Vector2f = Vector2f(0.0f, 0.0f), anchorPoint: Vector2f = Vector2f(0.0f, 0.0f), scale: Vector2f = Vector2f(1.0f, 1.0f), file: File): super(id, position, anchorPoint, scale) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = file

        val imageBuffer: BufferedImage = ImageIO.read(file)
        size = Vector2f(imageBuffer.width.toFloat(), imageBuffer.height.toFloat())
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f, 0.0f), anchorPoint: Vector2f = Vector2f(0.0f, 0.0f), scale: Vector2f = Vector2f(1.0f, 1.0f), size: Vector2f, file: File): super(id, position, anchorPoint, scale, size) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = file
        this.size = size
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f, 0.0f), anchorPoint: Vector2f = Vector2f(0.0f, 0.0f), scale: Vector2f = Vector2f(1.0f, 1.0f), filePath: String): super(id, position, anchorPoint, scale) {
        val file = File(filePath)
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = file

        val imageBuffer: BufferedImage = ImageIO.read(file)
        size = Vector2f(imageBuffer.width.toFloat(), imageBuffer.height.toFloat())
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vector2f = Vector2f(0.0f, 0.0f), anchorPoint: Vector2f = Vector2f(0.0f, 0.0f), scale: Vector2f = Vector2f(1.0f, 1.0f), size: Vector2f, filePath: String): super(id, position, anchorPoint, scale, size) {
        val file = File(filePath)
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = file
        this.size = size
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    var image: File
        private set

    fun setImage(file: File) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = file

        val imageBuffer: BufferedImage = ImageIO.read(file)
        size = Vector2f(imageBuffer.width.toFloat(), imageBuffer.height.toFloat())
    }

    fun setImage(filePath: String) {
        setImage(File(filePath))
    }

}