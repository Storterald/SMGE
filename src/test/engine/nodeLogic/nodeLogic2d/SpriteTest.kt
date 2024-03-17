package nodeLogic.nodeLogic2d

import org.joml.Vector2f
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.io.File

import renderEngine.createDisplay

class SpriteTest {
    init {
        createDisplay("Sprite Test")
    }

    // ### CONSTRUCTORS TEST ###
    // -------------------------

    @Test
    fun firstConstructor_createsSpriteWithoutThrowing() {
        assertDoesNotThrow { Sprite(file = File("src\\test\\resources\\dirt512x.png")) }
    }

    @Test
    fun firstConstructor_getsImageSize() {
        val sprite = Sprite(file = File("src\\test\\resources\\dirt512x.png"))
        assertEquals(512.0f, sprite.size.x)
        assertEquals(512.0f, sprite.size.y)
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
        assertDoesNotThrow { Sprite(size = Vector2f(100.0f, 100.0f), file = File("src\\test\\resources\\dirt512x.png")) }
    }


    @Test
    fun secondConstructor_throwsIfFileDoesNotExist() {
        assertThrows<IllegalStateException> { Sprite(size = Vector2f(100.0f, 100.0f), file = File("file.png")) }
    }

    @Test
    fun secondConstructor_throwsIfFileHasInvalidExtension() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vector2f(100.0f, 100.0f), file = File("src\\test\\resources\\testFile.txt")) }
    }

    @Test
    fun thirdConstructor_createsSpriteWithoutThrowing() {
        assertDoesNotThrow { Sprite(filePath = "src\\test\\resources\\dirt512x.png") }
    }

    @Test
    fun thirdConstructor_getsImageSize() {
        val sprite = Sprite(filePath = "src\\test\\resources\\dirt512x.png")
        assertEquals(512.0f, sprite.size.x)
        assertEquals(512.0f, sprite.size.y)
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
        assertDoesNotThrow { Sprite(size = Vector2f(100.0f, 100.0f), filePath = "src\\test\\resources\\dirt512x.png") }
    }

    @Test
    fun fourthConstructor_throwsIfFileDoesNotExist() {
        assertThrows<IllegalStateException> { Sprite(size = Vector2f(100.0f, 100.0f), filePath = "file.png") }
    }

    @Test
    fun fourthConstructor_throwsIfFileHasInvalidExtension() {
        assertThrows<IllegalArgumentException> { Sprite(size = Vector2f(100.0f, 100.0f), filePath = "src\\test\\resources\\testFile.txt") }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun firstSetImage_doesNotThrowIfValidAndChangesSize() {
        val sprite = Sprite(filePath = "src\\test\\resources\\dirt512x.png")
        assertDoesNotThrow { sprite.setImage(File("src\\test\\resources\\dirt256x.png")) }
        assertEquals(256.0f, sprite.size.x)
        assertEquals(256.0f, sprite.size.y)
    }

    @Test
    fun firstSetImage_throwsIfFileDoesNotExist() {
        val sprite = Sprite(filePath = "src\\test\\resources\\dirt512x.png")
        assertThrows<IllegalStateException> { sprite.setImage(File("thisFileDoesNotExist.png")) }
    }

    @Test
    fun firstSetImage_throwsIfFileExtensionIsInvalid() {
        val sprite = Sprite(filePath = "src\\test\\resources\\dirt512x.png")
        assertThrows<IllegalArgumentException> { sprite.setImage(File("src\\test\\resources\\testFile.txt")) }
    }

    @Test
    fun secondSetImage_doesNotThrowIfValidAndChangesSize() {
        val sprite = Sprite(filePath = "src\\test\\resources\\dirt512x.png")
        assertDoesNotThrow { sprite.setImage("src\\test\\resources\\dirt256x.png") }
        assertEquals(256.0f, sprite.size.x)
        assertEquals(256.0f, sprite.size.y)
    }

    @Test
    fun secondSetImage_throwsIfFileDoesNotExist() {
        val sprite = Sprite(filePath = "src\\test\\resources\\dirt512x.png")
        assertThrows<IllegalStateException> { sprite.setImage("thisFileDoesNotExist.png") }
    }

    @Test
    fun secondSetImage_throwsIfFileExtensionIsInvalid() {
        val sprite = Sprite(filePath = "src\\test\\resources\\dirt512x.png")
        assertThrows<IllegalArgumentException> { sprite.setImage("src\\test\\resources\\testFile.txt") }
    }

}