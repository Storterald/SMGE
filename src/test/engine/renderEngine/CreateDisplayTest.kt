package renderEngine

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*

class CreateDisplayTest {

    @Test fun constructor_createsDisplayWithoutThrowing() {
        assertDoesNotThrow { createDisplay("Title") }
    }

    @Test fun constructor_throwsIfNameIsEmpty() {
        assertThrows<IllegalArgumentException> { createDisplay("") }
    }

    @Test fun constructor_throwsIfNameIsOnlyMadeBySpaces() {
        assertThrows<IllegalArgumentException> { createDisplay("    ") }
    }

    @Test fun constructor_throwsIfNameHasSpaceAsFirstChar() {
        assertThrows<IllegalArgumentException> { createDisplay(" Title") }
    }

}