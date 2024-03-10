package nodeLogic.nodeLogic2d

import math.Vec2
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.io.File

class SpriteTest {

    // ### CONSTRUCTORS TEST ###
    // -------------------------

    @Test
    fun firstConstructor_createsSpriteWithoutThrowing() {
        assertDoesNotThrow { Sprite(file = File("src\\test\\resources\\testImage.png")) }
    }

    @Test
    fun firstConstructor_getsImageSize() {
        val sprite = Sprite(file = File("src\\test\\resources\\testImage.png"))
        assertEquals(1024.0f, sprite.size.x)
        assertEquals(1024.0f, sprite.size.y)
    }

    @Test
    fun firstConstructor_throwsIfFileDoesNotExist() {
        assertThrows<IllegalStateException> { Sprite(file = File("file.png")) }
    }

    @Test
    fun firstConstructor_throwsIfFileHasInvalidExtension() {
        assertThrows<IllegalArgumentException> { Sprite(file = File("src\\test\\resources\\testFile.txt")) }
    }

    @Test
    fun secondConstructor_createsSpriteWithoutThrowing() {
        assertDoesNotThrow { Sprite(size = Vec2(100.0f, 100.0f), file = File("src\\test\\resources\\testImage.png")) }
    }


    @Test
    fun secondConstructor_throwsIfFileDoesNotExist() {
        assertThrows<IllegalStateException> { Sprite(size = Vec2(100.0f, 100.0f), file = File("file.png")) }
    }

    @Test
    fun secondConstructor_throwsIfFileHasInvalidExtension() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(100.0f, 100.0f), file = File("src\\test\\resources\\testFile.txt")) }
    }

    @Test
    fun secondConstructor_throwsIfSizeXValueIsNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(-100.0f, 100.0f), file = File("src\\test\\resources\\testImage.png")) }
    }

    @Test
    fun secondConstructor_throwsIfSizeYValueIsNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(100.0f, -100.0f), file = File("src\\test\\resources\\testImage.png")) }
    }

    @Test
    fun secondConstructor_throwsIfSizeValuesAreNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(-100.0f, -100.0f), file = File("src\\test\\resources\\testImage.png")) }
    }

    @Test
    fun thirdConstructor_createsSpriteWithoutThrowing() {
        assertDoesNotThrow { Sprite(filePath = "src\\test\\resources\\testImage.png") }
    }

    @Test
    fun thirdConstructor_getsImageSize() {
        val sprite = Sprite(filePath = "src\\test\\resources\\testImage.png")
        assertEquals(1024.0f, sprite.size.x)
        assertEquals(1024.0f, sprite.size.y)
    }

    @Test
    fun thirdConstructor_throwsIfFileDoesNotExist() {
        assertThrows<IllegalStateException> { Sprite(filePath = "file.png") }
    }

    @Test
    fun thirdConstructor_throwsIfFileHasInvalidExtension() {
        assertThrows<IllegalArgumentException> { Sprite(filePath = "src\\test\\resources\\testFile.txt") }
    }

    @Test
    fun fourthConstructor_createsSpriteWithoutThrowing() {
        assertDoesNotThrow { Sprite(size = Vec2(100.0f, 100.0f), filePath = "src\\test\\resources\\testImage.png") }
    }

    @Test
    fun fourthConstructor_throwsIfFileDoesNotExist() {
        assertThrows<IllegalStateException> { Sprite(size = Vec2(100.0f, 100.0f), filePath = "file.png") }
    }

    @Test
    fun fourthConstructor_throwsIfFileHasInvalidExtension() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(100.0f, 100.0f), filePath = "src\\test\\resources\\testFile.txt") }
    }

    @Test
    fun fourthConstructor_throwsIfSizeXValueIsNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(-100.0f, 100.0f), filePath = "src\\test\\resources\\testImage.png") }
    }

    @Test
    fun fourthConstructor_throwsIfSizeYValueIsNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(100.0f, -100.0f), filePath = "src\\test\\resources\\testImage.png") }
    }

    @Test
    fun fourthConstructor_throwsIfSizeValuesAreNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(-100.0f, -100.0f), filePath = "src\\test\\resources\\testImage.png") }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun image_doesNotThrowIfValidAndChangesSize() {
        val sprite = Sprite(filePath = "src\\test\\resources\\testImage.png")
        assertDoesNotThrow { sprite.image = File("src\\test\\resources\\testImage2.png") }
        assertEquals(2500.0f, sprite.size.x)
        assertEquals(2455.0f, sprite.size.y)
    }

}