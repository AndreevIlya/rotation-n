package com.example.rotationsubn.ui.components.bottombar

sealed interface Parametrization {
    enum class Dim3 : Parametrization {
        YULER,
        TAIT_BRYAN,
        QUATERNIONS,
        QUATERNIONS_AXIS
    }
}
