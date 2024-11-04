package com.example.rotationsubn.ui.components.inputslider

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

    fun round() = type.round(value)
}
