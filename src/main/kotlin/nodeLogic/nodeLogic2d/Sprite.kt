package nodeLogic.nodeLogic2d

import math.Vec2
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Sprite: Object2D {
    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), file: File): super(id, position, anchorPoint, scale) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        val tempImage: BufferedImage = ImageIO.read(file)
        size = Vec2(tempImage.width.toFloat(), tempImage.height.toFloat())
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }

        image = file
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), size: Vec2, file: File): super(id, position, anchorPoint, scale, size) {
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        image = file
        this.size = size
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), filePath: String): super(id, position, anchorPoint, scale) {
        image = File(filePath)
        check(image.exists()) { "The sprite image file doesn't exist." }
        require(image.extension == "png" || image.extension == "jpeg" || image.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        val tempImage: BufferedImage = ImageIO.read(image)
        size = Vec2(tempImage.width.toFloat(), tempImage.height.toFloat())
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }

        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), size: Vec2, filePath: String): super(id, position, anchorPoint, scale, size) {
        image = File(filePath)
        check(image.exists()) { "The sprite image file doesn't exist." }
        require(image.extension == "png" || image.extension == "jpeg" || image.extension == "jpg") { "The image must be a png, jpeg or jpg." }

        this.size = size
        this.scale = scale
        this.anchorPoint = anchorPoint
    }

    var image: File = File("file")
        set(value) {
            check(value.exists()) { "The sprite image file doesn't exist." }
            require(value.extension == "png" || value.extension == "jpeg" || value.extension == "jpg") { "The image must be a png, jpeg or jpg." }

            val tempImage: BufferedImage = ImageIO.read(value)
            size = Vec2(tempImage.width.toFloat(), tempImage.height.toFloat())

            field = value
        }

}
