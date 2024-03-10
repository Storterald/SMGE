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
        assertDoesNotThrow { Sprite(file = File("src\\test\\resources\\testImage0.png")) }
    }

    @Test
    fun firstConstructor_getsImageSize() {
        val sprite = Sprite(file = File("src\\test\\resources\\testImage0.png"))
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
        assertDoesNotThrow { Sprite(size = Vec2(100.0f, 100.0f), file = File("src\\test\\resources\\testImage0.png")) }
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
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(-100.0f, 100.0f), file = File("src\\test\\resources\\testImage0.png")) }
    }

    @Test
    fun secondConstructor_throwsIfSizeYValueIsNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(100.0f, -100.0f), file = File("src\\test\\resources\\testImage0.png")) }
    }

    @Test
    fun secondConstructor_throwsIfSizeValuesAreNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(-100.0f, -100.0f), file = File("src\\test\\resources\\testImage0.png")) }
    }

    @Test
    fun thirdConstructor_createsSpriteWithoutThrowing() {
        assertDoesNotThrow { Sprite(filePath = "src\\test\\resources\\testImage0.png") }
    }

    @Test
    fun thirdConstructor_getsImageSize() {
        val sprite = Sprite(filePath = "src\\test\\resources\\testImage0.png")
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
        assertDoesNotThrow { Sprite(size = Vec2(100.0f, 100.0f), filePath = "src\\test\\resources\\testImage0.png") }
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
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(-100.0f, 100.0f), filePath = "src\\test\\resources\\testImage0.png") }
    }

    @Test
    fun fourthConstructor_throwsIfSizeYValueIsNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(100.0f, -100.0f), filePath = "src\\test\\resources\\testImage0.png") }
    }

    @Test
    fun fourthConstructor_throwsIfSizeValuesAreNegative() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vec2(-100.0f, -100.0f), filePath = "src\\test\\resources\\testImage0.png") }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun image_withFileDoesNotThrowIfValidAndChangesSize() {
        val sprite = Sprite(filePath = "src\\test\\resources\\testImage0.png")
        assertDoesNotThrow { sprite.setImage(File("src\\test\\resources\\testImage1.png")) }
        assertEquals(2500.0f, sprite.size.x)
        assertEquals(2455.0f, sprite.size.y)
    }

    @Test
    fun image_withFileThrowsIfFileDoesNotExist() {
        val sprite = Sprite(filePath = "src\\test\\resources\\testImage0.png")
        assertThrows<IllegalStateException> { sprite.setImage(File("thisFileDoesNotExist.png")) }
    }

    @Test
    fun image_withFileThrowsIfFileExtensionIsInvalid() {
        val sprite = Sprite(filePath = "src\\test\\resources\\testImage0.png")
        assertThrows<IllegalArgumentException> { sprite.setImage(File("src\\test\\resources\\testFile.txt")) }
    }

    @Test
    fun image_withFilePathDoesNotThrowIfValidAndChangesSize() {
        val sprite = Sprite(filePath = "src\\test\\resources\\testImage0.png")
        assertDoesNotThrow { sprite.setImage("src\\test\\resources\\testImage1.png") }
        assertEquals(2500.0f, sprite.size.x)
        assertEquals(2455.0f, sprite.size.y)
    }

    @Test
    fun image_withFilePathThrowsIfFileDoesNotExist() {
        val sprite = Sprite(filePath = "src\\test\\resources\\testImage0.png")
        assertThrows<IllegalStateException> { sprite.setImage("thisFileDoesNotExist.png") }
    }

    @Test
    fun image_withFilePathThrowsIfFileExtensionIsInvalid() {
        val sprite = Sprite(filePath = "src\\test\\resources\\testImage0.png")
        assertThrows<IllegalArgumentException> { sprite.setImage("src\\test\\resources\\testFile.txt") }
    }

}