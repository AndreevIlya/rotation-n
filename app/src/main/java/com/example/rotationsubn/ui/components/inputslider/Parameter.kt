package com.example.rotationsubn.ui.components.inputslider

import androidx.compose.ui.text.intl.Locale

data class Parameter(
    val title: String,
    val type: ParameterType,
    val suggestions: List<TitledValue<Float>>,
) {
    var value: Float = 0f
        set(value) {
            field = type.trim(value)
        }

    var isFixed: Boolean = false

    fun round(): String {
        val rounded = type.round(value)
        val precision = if (rounded >= 100) 1 else if (rounded >= 10) 2 else 3
        return String.format(
            locale = Locale.current.platformLocale,
            format = "%4.${precision}f",
            rounded
        )
    }
}
