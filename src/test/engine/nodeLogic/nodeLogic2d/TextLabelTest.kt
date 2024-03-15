package nodeLogic.nodeLogic2d

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import util.FontExtra
import java.awt.Font
import java.io.File

class TextLabelTest {

    // ### CONSTRUCTORS TEST ###
    // -------------------------

    @Test
    fun firstConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(text = "Test") }
    }

    @Test
    fun firstConstructor_getsImageSize() {
        val textLabel = TextLabel(text = "Test")
        assertEquals(24.0f, textLabel.size.x)
        assertEquals(14.0f, textLabel.size.y)
    }

    @Test
    fun secondConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(fontSize = 35.0f, text = "Test") }
    }

    @Test
    fun secondConstructor_getsImageSize() {
        val textLabel = TextLabel(fontSize = 35.0f, text = "Test")
        assertEquals(67.0f, textLabel.size.x)
        assertEquals(41.0f, textLabel.size.y)
    }

    @Test
    fun thirdConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(text = "Test", fontFile = File("src\\test\\resources\\testFont.otf")) }
    }

    @Test
    fun thirdConstructor_getsImageSize() {
        val textLabel = TextLabel(text = "Test", fontFile = File("src\\test\\resources\\testFont.otf"))
        assertEquals(26.0f, textLabel.size.x)
        assertEquals(15.0f, textLabel.size.y)
    }

    @Test
    fun fourthConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(fontSize = 35.0f, text = "Test", fontFile = File("src\\test\\resources\\testFont.otf")) }
    }

    @Test
    fun fourthConstructor_getsImageSize() {
        val textLabel = TextLabel(fontSize = 35.0f, text = "Test", fontFile = File("src\\test\\resources\\testFont.otf"))
        assertEquals(77.0f, textLabel.size.x)
        assertEquals(42.0f, textLabel.size.y)
    }

    @Test
    fun fifthConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(text = "Test", fontPath = "src\\test\\resources\\testFont.otf") }
    }

    @Test
    fun fifthConstructor_getsImageSize() {
        val textLabel = TextLabel(text = "Test", fontPath = "src\\test\\resources\\testFont.otf")
        assertEquals(26.0f, textLabel.size.x)
        assertEquals(15.0f, textLabel.size.y)
    }

    @Test
    fun sixthConstructor_createsTextLabelAndWithoutThrowing() {
        assertDoesNotThrow { TextLabel(fontSize = 35.0f, text = "Test", fontPath = "src\\test\\resources\\testFont.otf") }
    }

    @Test
    fun sixthConstructor_getsImageSize() {
        val textLabel = TextLabel(fontSize = 35.0f, text = "Test", fontPath = "src\\test\\resources\\testFont.otf")
        assertEquals(77.0f, textLabel.size.x)
        assertEquals(42.0f, textLabel.size.y)
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test
    fun fontSize_createsTheImageAndChangesTheSize() {
        val textLabel = TextLabel(text = "Test", fontPath = "src\\test\\resources\\testFont.otf")
        textLabel.fontSize = 35.0f

        assertEquals(77.0f, textLabel.size.x)
        assertEquals(42.0f, textLabel.size.y)
    }

    @Test
    fun fontSize_throwsIfValueIsNegative() {
        val textLabel = TextLabel(text = "Test", fontPath = "src\\test\\resources\\testFont.otf")
        assertThrows<IllegalArgumentException> { textLabel.fontSize = -1.0f }
    }
}