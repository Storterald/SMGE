package math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*

class Vec2Test {

    private var vec2 = Vec2(0f, 0f)

    @BeforeEach
    fun setup() {
        vec2 = Vec2(1f, 5f)
    }

    @Test
    fun equals_IfTwoVec2AreEqualsItReturnsTrue() {
        assert(vec2 == vec2)
    }

    @Test
    fun unaryMinus_AfterCallBothPropertiesOfVec2AreOfOppositeSign() {
        assertEquals(Vec2(-1f, -5f), -vec2)
    }

    @Test
    fun plus_TheTwoVectorsCorrespondingElementsAreSummed() {
        val toAdd = Vec2(1f, 2f)
        assertEquals(Vec2(2f, 7f), vec2 + toAdd)
    }

    @Test
    fun plus_WhenSummingAScalarToAVec2TheSumIsDistributed() {
        val toAdd = 10f
        assertEquals(Vec2(11f, 15f), vec2 + toAdd)
    }

    @Test
    fun minus_TheTwoVectorsCorrespondingElementsAreSubtracted() {
        val toSub = Vec2(1f, 2f)
        assertEquals(Vec2(0f, 3f), vec2 - toSub)
    }

    @Test
    fun minus_WhenSummingAScalarToAVec2TheSubtractionIsDistributed() {
        val toSub = 10f
        assertEquals(Vec2(-9f, -5f), vec2 - toSub)
    }

    @Test
    fun times_TheTwoVectorsCorrespondingElementsAreMultiplied() {
        val toMul = Vec2(1f, 2f)
        assertEquals(Vec2(1f, 10f), vec2 * toMul)
    }

    @Test
    fun times_WhenSummingAScalarToAVec2TheMultiplicationIsDistributed() {
        val toMul = 10f
        assertEquals(Vec2(10f, 50f), vec2 * toMul)
    }

    @Test
    fun div_TheTwoVectorsCorrespondingElementsAreDivided() {
        val toDiv = Vec2(1f, 2f)
        assertEquals(Vec2(1f, 2.5f), vec2 / toDiv)
    }

    @Test
    fun div_WhenSummingAScalarToAVec2TheDivisionIsDistributed() {
        val toDiv = 10f
        assertEquals(Vec2(0.1f, 0.5f), vec2 / toDiv)
    }

    @Test
    fun length_TheLengthOfTheVectorIsTheHypotenuseOfARightTriangleBuiltOnTheXAndYComponents() {
        val vec2 = Vec2(3f, 4f)
        assertEquals(5f, vec2.length())
    }

    @Test
    fun normalize_TheLengthOfANormalizedVec2IsOne() {
        assertEquals(1f, vec2.normalize().length())
    }

    @Test
    fun dot_TheDotProductOfTwoVectorsIsTheSumOfTheMultiplicationOfEachComponent() {
        val toDot = Vec2(1f, 2f)
        assertEquals(11f, vec2.dot(toDot))
    }
}