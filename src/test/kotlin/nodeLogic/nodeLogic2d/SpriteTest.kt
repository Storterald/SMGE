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
    fun firstConstructor_calculatesScaledSize() {
        val sprite = Sprite(scale = Vec2(0.5f, 0.5f), file = File("src\\test\\resources\\testImage.png"))
        assertEquals(512.0f, sprite.scaledSize.x)
        assertEquals(512.0f, sprite.scaledSize.y)
    }

    @Test
    fun firstConstructor_calculatesAnchoredPosition() {
        val sprite = Sprite(position = Vec2(100.0f, 100.0f), anchorPoint = Vec2(0.5f, 0.25f), file = File("src\\test\\resources\\testImage.png"))
        assertEquals(-412.0f, sprite.anchoredPosition.x)
        assertEquals(-156.0f, sprite.anchoredPosition.y)
    }

    @Test
    fun firstConstructor_calculatesAbsolutePosition() {
        val sprite = Sprite(position = Vec2(100.0f, 100.0f), anchorPoint = Vec2(0.25f, 0.50f), scale = Vec2(0.5f, 0.5f), file = File("src\\test\\resources\\testImage.png"))
        assertEquals(-28.0f, sprite.absolutePosition.x1)
        assertEquals(-156.0f, sprite.absolutePosition.y1)
        assertEquals(484.0f, sprite.absolutePosition.x2)
        assertEquals(356.0f, sprite.absolutePosition.y2)
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
    fun secondConstructor_calculatesScaledSize() {
        val sprite = Sprite(scale = Vec2(0.5f, 0.5f), size = Vec2(100.0f, 100.0f), file = File("src\\test\\resources\\testImage.png"))
        assertEquals(50.0f, sprite.scaledSize.x)
        assertEquals(50.0f, sprite.scaledSize.y)
    }

    @Test
    fun secondConstructor_calculatesAnchoredPosition() {
        val sprite = Sprite(position = Vec2(100.0f, 100.0f), anchorPoint = Vec2(0.5f, 0.25f), size = Vec2(100.0f, 100.0f), file = File("src\\test\\resources\\testImage.png"))
        assertEquals(50.0f, sprite.anchoredPosition.x)
        assertEquals(75.0f, sprite.anchoredPosition.y)
    }

    @Test
    fun secondConstructor_calculatesAbsolutePosition() {
        val sprite = Sprite(position = Vec2(100.0f, 100.0f), anchorPoint = Vec2(0.25f, 0.50f), scale = Vec2(0.5f, 0.5f), size = Vec2(100.0f, 100.0f), file = File("src\\test\\resources\\testImage.png"))
        assertEquals(87.5f, sprite.absolutePosition.x1)
        assertEquals(75.0f, sprite.absolutePosition.y1)
        assertEquals(137.5f, sprite.absolutePosition.x2)
        assertEquals(125.0f, sprite.absolutePosition.y2)
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
    fun thirdConstructor_calculatesScaledSize() {
        val sprite = Sprite(scale = Vec2(0.5f, 0.5f), filePath = "src\\test\\resources\\testImage.png")
        assertEquals(512.0f, sprite.scaledSize.x)
        assertEquals(512.0f, sprite.scaledSize.y)
    }

    @Test
    fun thirdConstructor_calculatesAnchoredPosition() {
        val sprite = Sprite(position = Vec2(100.0f, 100.0f), anchorPoint = Vec2(0.5f, 0.25f), filePath = "src\\test\\resources\\testImage.png")
        assertEquals(-412.0f, sprite.anchoredPosition.x)
        assertEquals(-156.0f, sprite.anchoredPosition.y)
    }

    @Test
    fun thirdConstructor_calculatesAbsolutePosition() {
        val sprite = Sprite(position = Vec2(100.0f, 100.0f), anchorPoint = Vec2(0.25f, 0.50f), scale = Vec2(0.5f, 0.5f), file = File("src\\test\\resources\\testImage.png"))
        assertEquals(-28.0f, sprite.absolutePosition.x1)
        assertEquals(-156.0f, sprite.absolutePosition.y1)
        assertEquals(484.0f, sprite.absolutePosition.x2)
        assertEquals(356.0f, sprite.absolutePosition.y2)
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
    fun fourthConstructor_calculatesScaledSize() {
        val sprite = Sprite(scale = Vec2(0.5f, 0.5f), size = Vec2(100.0f, 100.0f), file = File("src\\test\\resources\\testImage.png"))
        assertEquals(50.0f, sprite.scaledSize.x)
        assertEquals(50.0f, sprite.scaledSize.y)
    }

    @Test
    fun fourthConstructor_calculatesAnchoredPosition() {
        val sprite = Sprite(position = Vec2(100.0f, 100.0f), anchorPoint = Vec2(0.5f, 0.25f), size = Vec2(100.0f, 100.0f), filePath = "src\\test\\resources\\testImage.png")
        assertEquals(50.0f, sprite.anchoredPosition.x)
        assertEquals(75.0f, sprite.anchoredPosition.y)
    }

    @Test
    fun fourthConstructor_calculatesAbsolutePosition() {
        val sprite = Sprite(position = Vec2(100.0f, 100.0f), anchorPoint = Vec2(0.25f, 0.50f), scale = Vec2(0.5f, 0.5f), size = Vec2(100.0f, 100.0f), file = File("src\\test\\resources\\testImage.png"))
        assertEquals(87.5f, sprite.absolutePosition.x1)
        assertEquals(75.0f, sprite.absolutePosition.y1)
        assertEquals(137.5f, sprite.absolutePosition.x2)
        assertEquals(125.0f, sprite.absolutePosition.y2)
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

}