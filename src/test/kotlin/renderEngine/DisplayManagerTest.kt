package renderEngine

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*

class DisplayManagerTest {

    @Test
    fun constructor_createsDisplayWithoutThrowing() {
        assertDoesNotThrow { DisplayManager("Title") }
    }

    @Test
    fun constructor_throwsIfNameIsEmpty() {
        assertThrows<IllegalArgumentException> { DisplayManager("") }
    }

    @Test
    fun constructor_throwsIfNameIsOnlyMadeBySpaces() {
        assertThrows<IllegalArgumentException> { DisplayManager("           ") }
    }

    @Test
    fun constructor_throwsIfNameHasSpaceAsFirstChar() {
        assertThrows<IllegalArgumentException> { DisplayManager(" Title") }
    }

}