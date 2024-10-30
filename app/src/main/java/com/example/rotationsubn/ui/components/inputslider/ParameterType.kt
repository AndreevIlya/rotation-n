package com.example.rotationsubn.ui.components.inputslider

import kotlin.math.roundToInt

sealed class ParameterType(
    open var value: Float,
    val start: Float,
    val end: Float,
    val step: Float
) {
    abstract fun round(): Float
    fun trim(): Float = when {
        value > end -> end
        value < start -> start
        else -> value
    }

    data class Angle(override var value: Float) : ParameterType(value, 0f, 360f, 1f) {

        override fun round(): Float = when (val trimmed = trim()) {
            in 100f..360f -> (trimmed * 10).roundToInt() / 10f
            in 10f..<100f -> (trimmed * 100).roundToInt() / 100f
            in 0f..<10f -> (trimmed * 1000).roundToInt() / 1000f
            else -> 0.000f
        }
    }

    data class Quaternion(override var value: Float) : ParameterType(value, 0f, 1f, 0.001f) {
        override fun round(): Float = (trim() * 1000).roundToInt() / 1000f
    }
}
