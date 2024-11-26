package com.example.rotationsubn.ui.components.bottombar

import com.example.rotationsubn.core.Parametrization

sealed class ParametrizationButton(
    val parametrization: Parametrization,
    open val onSelected: () -> Unit
) {

    data class Yuler(override val onSelected: () -> Unit = { }) : ParametrizationButton(
        parametrization = Parametrization.Dim3.Yuler,
        onSelected = onSelected
    )

    data class TaitBryan(override val onSelected: () -> Unit = { }) : ParametrizationButton(
        parametrization = Parametrization.Dim3.TaitBryan,
        onSelected = onSelected
    )

    data class QuaternionAxis(override val onSelected: () -> Unit = { }) : ParametrizationButton(
        parametrization = Parametrization.Dim3.QuaternionsAxis,
        onSelected = onSelected
    )

    data class Quaternion(override val onSelected: () -> Unit = { }) : ParametrizationButton(
        parametrization = Parametrization.Dim3.Quaternions,
        onSelected = onSelected
    )

    companion object {

        fun List<ParametrizationButton>.byType(type: Parametrization.Dim3) = find {
            when (type) {
                Parametrization.Dim3.Yuler -> it is Yuler
                Parametrization.Dim3.TaitBryan -> it is TaitBryan
                Parametrization.Dim3.Quaternions -> it is Quaternion
                Parametrization.Dim3.QuaternionsAxis -> it is QuaternionAxis
            }
        } ?: throw Exception("Wrong parametrization type $type.")
    }
}
