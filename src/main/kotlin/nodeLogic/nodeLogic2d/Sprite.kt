package nodeLogic.nodeLogic2d

import math.Vec2
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Sprite: Object2D {
    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), file: File): super(id, position, anchorPoint, scale) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = ImageIO.read(file)
        size = Vec2(image.width.toFloat(), image.height.toFloat())
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), size: Vec2, file: File): super(id, position, anchorPoint, scale, size) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = ImageIO.read(file)
        this.size = size
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), filePath: String): super(id, position, anchorPoint, scale) {
        val file = File(filePath)
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = ImageIO.read(file)
        size = Vec2(image.width.toFloat(), image.height.toFloat())
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), size: Vec2, filePath: String): super(id, position, anchorPoint, scale, size) {
        val file = File(filePath)
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = ImageIO.read(file)
        this.size = size
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    var image: BufferedImage
        private set

    fun setImage(file: File) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = ImageIO.read(file)

        size = Vec2(image.width.toFloat(), image.height.toFloat())
    }

    fun setImage(filePath: String) {
        val file = File(filePath)
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = ImageIO.read(file)

        size = Vec2(image.width.toFloat(), image.height.toFloat())
    }

}
