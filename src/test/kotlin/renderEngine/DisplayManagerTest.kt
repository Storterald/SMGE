package renderEngine

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*

class DisplayManagerTest {

    @Test
    fun constructor_createsDisplayWithoutThrowing() {
        assertDoesNotThrow { DisplayManager("Title", TWO_DIMENSIONS) }
    }

    @Test
    fun constructor_throwsIfNameIsEmpty() {
        assertThrows<IllegalArgumentException> { DisplayManager("", TWO_DIMENSIONS) }
    }

    @Test
    fun constructor_throwsIfNameIsOnlyMadeBySpaces() {
        assertThrows<IllegalArgumentException> { DisplayManager("    ", TWO_DIMENSIONS) }
    }

    @Test
    fun constructor_throwsIfNameHasSpaceAsFirstChar() {
        assertThrows<IllegalArgumentException> { DisplayManager(" Title", TWO_DIMENSIONS) }
    }

}