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

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), size: Vec2, file: File): super(id, position, anchorPoint, scale) {
        require(size.x > 0.0f && size.y > 0.0f) { "The size must be higher than 0*0." }
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

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), size: Vec2, filePath: String): super(id, position, anchorPoint, scale) {
        require(size.x > 0.0f && size.y > 0.0f) { "The size must be higher than 0*0." }

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
            field = value
        }

    var size: Vec2 = Vec2(0.0f, 0.0f)
        set(value) {
            require(value.x > 0.0f && value.y > 0.0f) { "The size must be higher than 0*0." }

            scaledSize = value * scale
            anchoredPosition = position - (value * scaledSize)
            absolutePosition = Rectangle(anchoredPosition, scaledSize)

            field = value
        }

    override var scale: Vec2 = Vec2(1.0f, 1.0f)
        set(value) {
            require(value.x >= 0.0f) { "The x scale must be positive" }
            require(value.y >= 0.0f) { "The y scale must be positive" }

            scaledSize = value * size
            anchoredPosition = position - (value * scaledSize)
            absolutePosition = Rectangle(anchoredPosition, scaledSize)

            field = value
        }

    override var anchorPoint: Vec2 = Vec2(0.0f, 0.0f)
        set(value) {
            require(value.x in 0.0f..1.0f) { "The x position must be within 0.0 and 1.0" }
            require(value.y in 0.0f..1.0f) { "The y position must be within 0.0 and 1.0" }

            anchoredPosition = position - (value * scaledSize)
            absolutePosition = Rectangle(anchoredPosition, scaledSize)

            field = value
        }

    var scaledSize: Vec2 = Vec2(0.0f, 0.0f)
        private set

    var anchoredPosition: Vec2 = Vec2(0.0f, 0.0f)
        private set

    var absolutePosition: Rectangle = Rectangle(0.0f, 0.0f, 0.0f, 0.0f)
        private set

}
