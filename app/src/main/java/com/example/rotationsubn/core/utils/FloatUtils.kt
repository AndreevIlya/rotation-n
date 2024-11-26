package com.example.rotationsubn.core.utils

import androidx.compose.ui.text.intl.Locale
import java.text.NumberFormat

fun String.toFloatByUSLocale(): Float {
    val numberFormat = NumberFormat.getNumberInstance(Locale("en_US").platformLocale)
    return if (isEmpty()) 0f else numberFormat.parse(this)?.toFloat() ?: 0f
}

fun String.filterToFloat(onSuccess: (Float) -> Unit): String = when {
    startsWith(".") || startsWith(",") || startsWith("-") -> ""
    endsWith(".,") || endsWith("..") -> dropLast(1)
    endsWith(",") -> dropLast(1)
    else -> {
        onSuccess(this.toFloatByUSLocale())
        this
    }
}
