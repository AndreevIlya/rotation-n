package com.example.rotationsubn.ui.components.bottombar

import androidx.annotation.DrawableRes
import com.example.rotationsubn.R

sealed class ParametrizationButton(
    @DrawableRes val iconRes: Int,
    val label: String,
    open val onSelected: () -> Unit
) {

    val parametrization: Parametrization.Dim3
        get() = when (this) {
            is Yuler -> Parametrization.Dim3.YULER
            is TaitBryan -> Parametrization.Dim3.TAIT_BRYAN
            is QuaternionAxis -> Parametrization.Dim3.QUATERNIONS_AXIS
            is Quaternion -> Parametrization.Dim3.QUATERNIONS
        }

    data class Yuler(override val onSelected: () -> Unit = { }) : ParametrizationButton(
        iconRes = R.drawable.ic_params_yuler,
        label = YULER,
        onSelected = onSelected
    )

    data class TaitBryan(override val onSelected: () -> Unit = { }) : ParametrizationButton(
        iconRes = R.drawable.ic_params_tait_bryan,
        label = TAIT_BRYAN,
        onSelected = onSelected
    )

    data class QuaternionAxis(override val onSelected: () -> Unit = { }) : ParametrizationButton(
        iconRes = R.drawable.ic_params_quaternions_axis,
        label = QUATERNIONS_AXIS,
        onSelected = onSelected
    )

    data class Quaternion(override val onSelected: () -> Unit = { }) : ParametrizationButton(
        iconRes = R.drawable.ic_params_quaternions,
        label = QUATERNIONS,
        onSelected = onSelected
    )

    companion object {
        const val YULER = "parametrization button Yuler"
        const val TAIT_BRYAN = "parametrization button Tait-Bryan"
        const val QUATERNIONS_AXIS =
            "parametrization button quaternions axis"
        const val QUATERNIONS = "parametrization button quaternions"

        fun List<ParametrizationButton>.byType(type: Parametrization.Dim3) = find {
            when (type) {
                Parametrization.Dim3.YULER -> it is Yuler
                Parametrization.Dim3.TAIT_BRYAN -> it is TaitBryan
                Parametrization.Dim3.QUATERNIONS -> it is Quaternion
                Parametrization.Dim3.QUATERNIONS_AXIS -> it is QuaternionAxis
            }
        } ?: throw Exception("Wrong parametrization type $type.")
    }
}
