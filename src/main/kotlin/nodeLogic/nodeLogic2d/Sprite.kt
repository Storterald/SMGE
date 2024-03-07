package nodeLogic.nodeLogic2d

import math.Vec2
import java.io.File

class Sprite: Object2D {
    private var file: File

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), file: File): super(id, position, anchorPoint, scale) {
        this.file = file
        check(file.exists()) { "The sprite image file doesn't exist." }
    }

    constructor(id: String = "", position: Vec2 = Vec2(0.0f, 0.0f), anchorPoint: Vec2 = Vec2(0.0f, 0.0f), scale: Vec2 = Vec2(1.0f, 1.0f), filePath: String): super(id, position, anchorPoint, scale) {
        this.file = File(filePath)
        check(file.exists()) { "The sprite image file doesn't exist." }
    }

}