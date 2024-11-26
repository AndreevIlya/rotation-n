package com.example.rotationsubn.core

import androidx.annotation.DrawableRes
import com.example.rotationsubn.R
import kotlin.math.sqrt

sealed class Parametrization(
    open val label: String,
    @DrawableRes open val iconRes: Int,
    open val parameters: List<Parameter>
) {
    sealed class Dim3(
        override val label: String,
        @DrawableRes override val iconRes: Int,
        override val parameters: List<Parameter>
    ) : Parametrization(label, iconRes, parameters) {
        data object Yuler : Dim3(
            label = YULER,
            iconRes = R.drawable.ic_params_yuler,
            parameters = listOf(
                Parameter(
                    title = R.string.parametrization_dim3_yuler_nutation,
                    type = ParameterType.Angle,
                    suggestions = listOf(
                        TitledValue<Float>("45", 45f),
                        TitledValue<Float>("90", 90f),
                        TitledValue<Float>("135", 135f)
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_yuler_precession,
                    type = ParameterType.Angle,
                    suggestions = listOf(
                        TitledValue<Float>("45", 45f),
                        TitledValue<Float>("90", 90f),
                        TitledValue<Float>("135", 135f)
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_yuler_rotation,
                    type = ParameterType.Angle,
                    suggestions = listOf(
                        TitledValue<Float>("45", 45f),
                        TitledValue<Float>("90", 90f),
                        TitledValue<Float>("135", 135f)
                    )
                )
            )
        )

        data object TaitBryan : Dim3(
            label = TAIT_BRYAN,
            iconRes = R.drawable.ic_params_tait_bryan,
            parameters = listOf(
                Parameter(
                    title = R.string.parametrization_dim3_tait_bryan_pitch,
                    type = ParameterType.Angle,
                    suggestions = listOf(
                        TitledValue<Float>("45", 45f),
                        TitledValue<Float>("90", 90f),
                        TitledValue<Float>("135", 135f)
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_tait_bryan_yaw,
                    type = ParameterType.Angle,
                    suggestions = listOf(
                        TitledValue<Float>("45", 45f),
                        TitledValue<Float>("90", 90f),
                        TitledValue<Float>("135", 135f)
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_tait_bryan_roll,
                    type = ParameterType.Angle,
                    suggestions = listOf(
                        TitledValue<Float>("45", 45f),
                        TitledValue<Float>("90", 90f),
                        TitledValue<Float>("135", 135f)
                    )
                )
            )
        )

        data object Quaternions : Dim3(
            label = QUATERNIONS,
            iconRes = R.drawable.ic_params_quaternions,
            parameters = listOf(
                Parameter(
                    title = R.string.parametrization_dim3_quaternion_0,
                    type = ParameterType.Quaternion,
                    suggestions = listOf(
                        TitledValue<Float>("1/2", 0.5f),
                        TitledValue<Float>("1/\u221A2", 1 / sqrt(2f)),
                        TitledValue<Float>("\u221A3/2", sqrt(3f) / 2),
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_quaternion_1,
                    type = ParameterType.Quaternion,
                    suggestions = listOf(
                        TitledValue<Float>("1/2", 0.5f),
                        TitledValue<Float>("1/\u221A2", 1 / sqrt(2f)),
                        TitledValue<Float>("\u221A3/2", sqrt(3f) / 2),
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_quaternion_2,
                    type = ParameterType.Quaternion,
                    suggestions = listOf(
                        TitledValue<Float>("1/2", 0.5f),
                        TitledValue<Float>("1/\u221A2", 1 / sqrt(2f)),
                        TitledValue<Float>("\u221A3/2", sqrt(3f) / 2),
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_quaternion_3,
                    type = ParameterType.Quaternion,
                    suggestions = listOf(
                        TitledValue<Float>("1/2", 0.5f),
                        TitledValue<Float>("1/\u221A2", 1 / sqrt(2f)),
                        TitledValue<Float>("\u221A3/2", sqrt(3f) / 2),
                    )
                )
            )
        )

        data object QuaternionsAxis : Dim3(
            label = QUATERNIONS_AXIS,
            iconRes = R.drawable.ic_params_quaternions_axis,
            parameters = listOf(
                Parameter(
                    title = R.string.parametrization_dim3_quaternion_axis_1,
                    type = ParameterType.Quaternion,
                    suggestions = listOf(
                        TitledValue<Float>("1/2", 0.5f),
                        TitledValue<Float>("1/\u221A2", 1 / sqrt(2f)),
                        TitledValue<Float>("\u221A3/2", sqrt(3f) / 2),
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_quaternion_axis_2,
                    type = ParameterType.Quaternion,
                    suggestions = listOf(
                        TitledValue<Float>("1/2", 0.5f),
                        TitledValue<Float>("1/\u221A2", 1 / sqrt(2f)),
                        TitledValue<Float>("\u221A3/2", sqrt(3f) / 2),
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_quaternion_axis_3,
                    type = ParameterType.Quaternion,
                    suggestions = listOf(
                        TitledValue<Float>("1/2", 0.5f),
                        TitledValue<Float>("1/\u221A2", 1 / sqrt(2f)),
                        TitledValue<Float>("\u221A3/2", sqrt(3f) / 2),
                    )
                ),
                Parameter(
                    title = R.string.parametrization_dim3_quaternion_axis_rotation,
                    type = ParameterType.Angle,
                    suggestions = listOf(
                        TitledValue<Float>("45", 45f),
                        TitledValue<Float>("90", 90f),
                        TitledValue<Float>("135", 135f)
                    )
                )
            )
        )

        companion object {
            const val YULER = "Yuler"
            const val TAIT_BRYAN = "Tait-Bryan"
            const val QUATERNIONS_AXIS = "quaternions axis"
            const val QUATERNIONS = "quaternions"
        }
    }
}
