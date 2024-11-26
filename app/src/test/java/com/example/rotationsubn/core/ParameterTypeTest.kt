package com.example.rotationsubn.core

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ParameterTypeTest {

    private val angle = ParameterType.Angle
    private val quaternion = ParameterType.Quaternion

    @Test
    fun angleTrimOver360() {
        assertEquals(360f, angle.round(400f))
    }

    @Test
    fun angleTrimBelow0() {
        assertEquals(0f, angle.round(-400f))
    }

    @Test
    fun angleRound() {
        assertEquals(220.3f, angle.round(220.3456f))
    }

    @Test
    fun quaternionTrimOver1() {
        assertEquals(1f, quaternion.round(4f))
    }

    @Test
    fun quaternionTrimBelow0() {
        assertEquals(0f, quaternion.round(-0.3f))
    }

    @Test
    fun quaternionRound() {
        assertEquals(0.568f, quaternion.round(0.56789f))
    }
}
