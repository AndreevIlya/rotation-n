package com.example.rotationsubn.core

import kotlin.math.roundToInt

sealed class ParameterType(
    val start: Float,
    val end: Float,
    val step: Float
) {
    abstract fun round(value: Float): Float
    fun trim(value: Float): Float = value.coerceIn(start, end)

    data object Angle : ParameterType(0f, 360f, 1f) {

        override fun round(value: Float): Float = when (val trimmed = trim(value)) {
            in 100f..360f -> (trimmed * 10).roundToInt() / 10f
            in 10f..<100f -> (trimmed * 100).roundToInt() / 100f
            in 0f..<10f -> (trimmed * 1000).roundToInt() / 1000f
            else -> 0.000f
        }
    }

    data object Quaternion : ParameterType(0f, 1f, 0.001f) {
        override fun round(value: Float): Float = (trim(value) * 1000).roundToInt() / 1000f
    }
}
