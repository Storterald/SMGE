package nodes.nodes2d

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import renderEngine.createDisplay
import java.awt.Font
import java.io.File

class TextLabelTest {
    init {
        createDisplay("TextLabel Test")
    }

    // ### CONSTRUCTORS TEST ###
    // -------------------------

    @Test fun firstConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(text = "Test") }
    }

    @Test fun firstConstructor_getsImageSize() {
        val textLabel = TextLabel(text = "Test")
        assertEquals(24.0f, textLabel.size.x)
        assertEquals(14.0f, textLabel.size.y)
    }

    @Test fun firstConstructor_createsAllCharLabelChildren() {
        val textLabel = TextLabel(text = "Test")
        assertEquals(4, textLabel.getChildrenCount())
    }

    @Test fun secondConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(fontSize = 35.0f, text = "Test") }
    }

    @Test fun secondConstructor_getsImageSize() {
        val textLabel = TextLabel(fontSize = 35.0f, text = "Test")
        assertEquals(67.0f, textLabel.size.x)
        assertEquals(41.0f, textLabel.size.y)
    }

    @Test fun secondConstructor_createsAllCharLabelChildren() {
        val textLabel = TextLabel(fontSize = 35.0f, text = "Test")
        assertEquals(4, textLabel.getChildrenCount())
    }

    @Test fun secondConstructor_throwsIfFontSizeIs0() {
        assertThrows<IllegalArgumentException> { TextLabel(fontSize = 0.0f, text = "Test") }
    }

    @Test fun secondConstructor_throwsIfFontSizeIsNegative() {
        assertThrows<IllegalArgumentException> { TextLabel(fontSize = -1.0f, text = "Test") }
    }

    @Test fun thirdConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(text = "Test", fontFile = File("src\\test\\resources\\Minecraft Regular.otf")) }
    }

    @Test fun thirdConstructor_getsImageSize() {
        val textLabel = TextLabel(text = "Test", fontFile = File("src\\test\\resources\\Minecraft Regular.otf"))
        assertEquals(26.0f, textLabel.size.x)
        assertEquals(15.0f, textLabel.size.y)
    }

    @Test fun thirdConstructor_createsAllCharLabelChildren() {
        val textLabel = TextLabel(text = "Test", fontFile = File("src\\test\\resources\\Minecraft Regular.otf"))
        assertEquals(4, textLabel.getChildrenCount())
    }

    @Test fun fourthConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(fontSize = 35.0f, text = "Test", fontFile = File("src\\test\\resources\\Minecraft Regular.otf")) }
    }

    @Test fun fourthConstructor_getsImageSize() {
        val textLabel = TextLabel(fontSize = 35.0f, text = "Test", fontFile = File("src\\test\\resources\\Minecraft Regular.otf"))
        assertEquals(77.0f, textLabel.size.x)
        assertEquals(42.0f, textLabel.size.y)
    }

    @Test fun fourthConstructor_createsAllCharLabelChildren() {
        val textLabel = TextLabel(fontSize = 35.0f, text = "Test", fontFile = File("src\\test\\resources\\Minecraft Regular.otf"))
        assertEquals(4, textLabel.getChildrenCount())
    }

    @Test fun fifthConstructor_createsTextLabelWithoutThrowing() {
        assertDoesNotThrow { TextLabel(text = "Test", fontPath = "src\\test\\resources\\Minecraft Regular.otf") }
    }

    @Test fun fifthConstructor_getsImageSize() {
        val textLabel = TextLabel(text = "Test", fontPath = "src\\test\\resources\\Minecraft Regular.otf")
        assertEquals(26.0f, textLabel.size.x)
        assertEquals(15.0f, textLabel.size.y)
    }

    @Test fun fifthConstructor_createsAllCharLabelChildren() {
        val textLabel = TextLabel(text = "Test", fontPath = "src\\test\\resources\\Minecraft Regular.otf")
        assertEquals(4, textLabel.getChildrenCount())
    }

    @Test fun sixthConstructor_createsTextLabelAndWithoutThrowing() {
        assertDoesNotThrow { TextLabel(fontSize = 35.0f, text = "Test", fontPath = "src\\test\\resources\\Minecraft Regular.otf") }
    }

    @Test fun sixthConstructor_getsImageSize() {
        val textLabel = TextLabel(fontSize = 35.0f, text = "Test", fontPath = "src\\test\\resources\\Minecraft Regular.otf")
        assertEquals(77.0f, textLabel.size.x)
        assertEquals(42.0f, textLabel.size.y)
    }

    @Test fun sixthConstructor_createsAllCharLabelChildren() {
        val textLabel = TextLabel(fontSize = 35.0f, text = "Test", fontPath = "src\\test\\resources\\Minecraft Regular.otf")
        assertEquals(4, textLabel.getChildrenCount())
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test fun setText_changesTheText() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setText("Different text")

        assertEquals("Different text", textLabel.text)
    }

    @Test fun setText_changesTheSize() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setText("Different text")

        assertEquals(67.0f, textLabel.size.x)
        assertEquals(14.0f, textLabel.size.y)
    }

    @Test fun firstSetFont_changesTheFont() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setFont(Font.createFont(Font.TRUETYPE_FONT, File("src\\test\\resources\\Minecraft Regular.otf")))

        assertEquals("Minecraft Regular", textLabel.font.fontName)
    }

    @Test fun firstSetFont_changesTheSize() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setFont(Font.createFont(Font.TRUETYPE_FONT, File("src\\test\\resources\\Minecraft Regular.otf")))

        assertEquals(26.0f, textLabel.size.x)
        assertEquals(15.0f, textLabel.size.y)
    }

    @Test fun secondSetFont_changesTheFont() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setFont(File("src\\test\\resources\\Minecraft Regular.otf"))

        assertEquals("Minecraft Regular", textLabel.font.fontName)
    }

    @Test fun secondSetFont_changesTheSize() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setFont(File("src\\test\\resources\\Minecraft Regular.otf"))

        assertEquals(26.0f, textLabel.size.x)
        assertEquals(15.0f, textLabel.size.y)
    }

    @Test fun secondSetFont_throwsIfFileDoesNotExist() {
        val textLabel = TextLabel(text = "Test")
        assertThrows<IllegalStateException>{ textLabel.setFont(File("file.otf")) }
    }

    @Test fun secondSetFont_throwsIfFileHasInvalidExtension() {
        val textLabel = TextLabel(text = "Test")
        assertThrows<IllegalArgumentException>{ textLabel.setFont(File("src\\test\\resources\\testFile.txt")) }
    }

    @Test fun thirdSetFont_changesTheFont() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setFont("src\\test\\resources\\Minecraft Regular.otf")

        assertEquals("Minecraft Regular", textLabel.font.fontName)
    }

    @Test fun thirdSetFont_changesTheSize() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setFont("src\\test\\resources\\Minecraft Regular.otf")

        assertEquals(26.0f, textLabel.size.x)
        assertEquals(15.0f, textLabel.size.y)
    }

    @Test fun setFontSize_changesTheFontSize() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setFontSize(35.0f)

        assertEquals(35.0f, textLabel.font.size2D)
    }

    @Test fun setFontSize_changesSize() {
        val textLabel = TextLabel(text = "Test")
        textLabel.setFontSize(35.0f)

        assertEquals(67.0f, textLabel.size.x)
        assertEquals(41.0f, textLabel.size.y)
    }

}