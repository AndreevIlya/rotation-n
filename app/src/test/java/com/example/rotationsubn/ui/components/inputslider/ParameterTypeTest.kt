package com.example.rotationsubn.ui.components.inputslider

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ParameterTypeTest {

    @Test
    fun angleTrimOver360() {
        val angle = ParameterType.Angle(400f)
        assertEquals(360f, angle.round())
    }

    @Test
    fun angleTrimBelow0() {
        val angle = ParameterType.Angle(-400f)
        assertEquals(0f, angle.round())
    }

    @Test
    fun angleRound() {
        val angle = ParameterType.Angle(220.3456f)
        assertEquals(220.3f, angle.round())
    }

    @Test
    fun quaternionTrimOver1() {
        val quaternion = ParameterType.Quaternion(4f)
        assertEquals(1f, quaternion.round())
    }

    @Test
    fun quaternionTrimBelow0() {
        val quaternion = ParameterType.Quaternion(-0.3f)
        assertEquals(0f, quaternion.round())
    }

    @Test
    fun quaternionRound() {
        val quaternion = ParameterType.Quaternion(0.56789f)
        assertEquals(0.568f, quaternion.round())
    }
}
