package math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import kotlin.math.sqrt

class Vec3Test {

    private var vec3 = Vec3(0f, 0f, 0f)

    @BeforeEach
    fun setup() {
        vec3 = Vec3(1f, 5f, 2f)
    }

    @Test
    fun equals_IfTwoVec3AreEqualsItReturnsTrue() {
        assert(vec3 == vec3)
    }

    @Test
    fun unaryMinus_AfterCallBothPropertiesOfVec3AreOfOppositeSign() {
        assertEquals(Vec3(-1f, -5f, -2f), -vec3)
    }

    @Test
    fun plus_TheTwoVectorsCorrespondingElementsAreSummed() {
        val toAdd = Vec3(1f, 2f, 1f)
        assertEquals(Vec3(2f, 7f, 3f), vec3 + toAdd)
    }

    @Test
    fun plus_WhenSummingAScalarToAVec3TheSumIsDistributed() {
        val toAdd = 10f
        assertEquals(Vec3(11f, 15f, 12f), vec3 + toAdd)
    }

    @Test
    fun minus_TheTwoVectorsCorrespondingElementsAreSubtracted() {
        val toSub = Vec3(1f, 2f, 1f)
        assertEquals(Vec3(0f, 3f, 1f), vec3 - toSub)
    }

    @Test
    fun minus_WhenSummingAScalarToAVec3TheSubtractionIsDistributed() {
        val toSub = 10f
        assertEquals(Vec3(-9f, -5f, -8f), vec3 - toSub)
    }

    @Test
    fun times_TheTwoVectorsCorrespondingElementsAreMultiplied() {
        val toMul = Vec3(1f, 2f, 2f)
        assertEquals(Vec3(1f, 10f, 4f), vec3 * toMul)
    }

    @Test
    fun times_WhenSummingAScalarToAVec3TheMultiplicationIsDistributed() {
        val toMul = 10f
        assertEquals(Vec3(10f, 50f, 20f), vec3 * toMul)
    }

    @Test
    fun div_TheTwoVectorsCorrespondingElementsAreDivided() {
        val toDiv = Vec3(1f, 2f, 1f)
        assertEquals(Vec3(1f, 2.5f, 2f), vec3 / toDiv)
    }

    @Test
    fun div_WhenSummingAScalarToAVec3TheDivisionIsDistributed() {
        val toDiv = 10f
        assertEquals(Vec3(0.1f, 0.5f, 0.2f), vec3 / toDiv)
    }

    @Test
    fun length_TheLengthOfTheVectorIsTheHypotenuseOfARightTriangleBuiltOnTheXAndYComponents() {
        val vec3 = Vec3(3f, 4f, 5f)
        assertEquals(sqrt(50f), vec3.length())
    }

    @Test
    fun normalize_TheLengthOfANormalizedVec3IsOne() {
        assertEquals(0.99999994f, vec3.normalize().length())
    }

    @Test
    fun dot_TheDotProductOfTwoVectorsIsTheSumOfTheMultiplicationOfEachComponent() {
        val toDot = Vec3(1f, 2f, 5f)
        assertEquals(21f, vec3.dot(toDot))
    }
}