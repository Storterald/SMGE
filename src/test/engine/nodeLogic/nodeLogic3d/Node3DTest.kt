package nodeLogic.nodeLogic3d

import org.joml.Vector3f
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class Node3DTest {

    // ### CONSTRUCTOR TEST ###
    // ------------------------

    @Test fun constructor_doesNotThrowIfAnchorPointIsValid() {
        assertDoesNotThrow { Node3D(position = Vector3f(1.0f, 0.75f, 0.5f)) }
    }

    @Test fun constructor_throwsIfXAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(anchorPoint = Vector3f(-1.0f, 0.0f, 0.0f)) }
    }

    @Test fun constructor_throwsIfYAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(anchorPoint = Vector3f(0.0f, -1.0f, 0.0f)) }
    }

    @Test fun constructor_throwsIfZAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(anchorPoint = Vector3f(0.0f, 0.0f, -1.0f)) }
    }

    @Test fun constructor_throwsIfAnchorPointIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(anchorPoint = Vector3f(-1.0f, -1.0f, -1.0f)) }
    }

    @Test fun constructor_throwsIfXAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node3D(anchorPoint = Vector3f(1.1f, 0.0f, 0.0f)) }
    }

    @Test fun constructor_throwsIfYAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node3D(anchorPoint = Vector3f(0.0f, 1.1f, 0.0f)) }
    }

    @Test fun constructor_throwsIfZAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node3D(anchorPoint = Vector3f(0.0f, 0.0f, 1.1f)) }
    }

    @Test fun constructor_throwsIfAnchorPointIsHigherThanOne() {
        assertThrows<IllegalArgumentException> { Node3D(anchorPoint = Vector3f(1.1f, 1.1f, 1.1f)) }
    }

    @Test fun constructor_doesNotThrowIfScaleIsValid() {
        assertDoesNotThrow { Node3D(scale = Vector3f(1.0f, 0.75f, 1.0f)) }
    }

    @Test fun constructor_throwsIfXScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(scale = Vector3f(-1.0f, 0.0f, 0.0f)) }
    }

    @Test fun constructor_throwsIfYScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(scale = Vector3f(0.0f, -1.0f, 0.0f)) }
    }

    @Test fun constructor_throwsIfZScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(scale = Vector3f(0.0f, 0.0f, -1.0f)) }
    }

    @Test fun constructor_throwsIfScaleIsNegative() {
        assertThrows<IllegalArgumentException> { Node3D(scale = Vector3f(-1.0f, -1.0f, -1.0f)) }
    }

    // ### SETTERS TEST ###
    // --------------------

    @Test fun anchorPoint_doesNotThrowIfValid() {
        val node = Node3D()
        assertDoesNotThrow { node.anchorPoint = Vector3f(1.0f, 0.5f, 0.3f) }
    }

    @Test fun anchorPoint_throwsIfXValueIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector3f(-1.0f, 0.0f, 0.0f) }
    }

    @Test fun anchorPoint_throwsIfYValueIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector3f(0.0f, -1.0f, 0.3f) }
    }
    @Test fun anchorPoint_throwsIfZValueIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector3f(0.0f, 1.0f, -0.3f) }
    }

    @Test fun anchorPoint_throwsIfValuesAreNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector3f(-1.0f, -1.0f, -1.0f) }
    }

    @Test fun anchorPoint_throwsIfXValueIsHigherThanOne() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector3f(1.1f, 0.0f, 0.0f) }
    }

    @Test fun anchorPoint_throwsIfYValueIsHigherThanOne() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector3f(0.0f, 1.1f, 0.0f) }
    }

    @Test fun anchorPoint_throwsIfZValueIsHigherThanOne() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector3f(0.0f, 0.0f, 1.1f) }
    }

    @Test fun anchorPoint_throwsIfValuesAreHigherThanOne() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.anchorPoint = Vector3f(1.1f, 1.1f, 1.1f) }
    }

    @Test fun scale_doesNotThrowIfScaleIsValid() {
        val node = Node3D()
        assertDoesNotThrow { node.scale = Vector3f(1.0f, 0.75f, 0.3f) }
    }

    @Test fun scale_throwsIfXValueIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.scale = Vector3f(-1.0f, 0.0f, 0.0f) }
    }

    @Test fun scale_throwsIfYValueIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.scale = Vector3f(0.0f, -1.0f, 0.0f) }
    }

    @Test fun scale_throwsIfZValueIsNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.scale = Vector3f(0.0f, 0.0f, -1.0f) }
    }

    @Test fun scale_throwsIfValuesAreNegative() {
        val node = Node3D()
        assertThrows<IllegalArgumentException> { node.scale = Vector3f(-1.0f, -1.0f, -1.0f) }
    }

}