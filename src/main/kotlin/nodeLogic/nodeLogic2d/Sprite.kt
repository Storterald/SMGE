package nodeLogic.nodeLogic2d

import math.Vec2
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Sprite: Object2D {
    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), file: File): super(id, position, anchorPoint, scale) {
        this.file = file
        this.scale = scale
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }
        val image: BufferedImage = ImageIO.read(file)
        size = Vec2(image.width.toFloat(), image.height.toFloat()) * scale
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), size: Vec2, file: File): super(id, position, anchorPoint, scale) {
        require(size.x > 0.0f && size.y > 0.0f) { "The size must be higher than 0*0." }
        this.file = file
        this.size = size * scale
        this.scale = scale
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), filePath: String): super(id, position, anchorPoint, scale) {
        this.file = File(filePath)
        this.scale = scale
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }
        val image: BufferedImage = ImageIO.read(file)
        size = Vec2(image.width.toFloat(), image.height.toFloat()) * scale
        check(size.x != 0.0f && size.y != 0.0f) { "Error initializing the image." }
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), size: Vec2, filePath: String): super(id, position, anchorPoint, scale) {
        require(size.x > 0.0f && size.y > 0.0f) { "The size must be higher than 0*0." }
        this.file = File(filePath)
        this.size = size * scale
        this.scale = scale
        check(file.exists()) { "The sprite image file doesn't exist." }
        require(file.extension == "png" || file.extension == "jpeg" || file.extension == "jpg") { "The image must be a png, jpeg or jpg." }
    }

    var file: File
    var size: Vec2 = Vec2(0.0f, 0.0f)
        set(value) {
            require(value.x > 0.0f && value.y > 0.0f) { "The size must be higher than 0*0." }
            field = value * scale
            absolutePosition = Rectangle(position, field.x, field.y)
        }

    var absolutePosition: Rectangle = Rectangle(position, size.x, size.y)
        private set

}
