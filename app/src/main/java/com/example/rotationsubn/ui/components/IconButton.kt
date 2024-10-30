package com.example.rotationsubn.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.DpSize

@Composable
fun IconButton(
    modifier: Modifier,
    semantics: SemanticsPropertyReceiver.() -> Unit,
    size: DpSize,
    icon: IconData?
) {
    Box(
        modifier = Modifier
            .semantics { semantics() }
            .size(size) then modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (icon != null) Icon(
            imageVector = ImageVector.vectorResource(id = icon.res),
            tint = icon.tint,
            contentDescription = icon.description,
        )
    }
}

data class IconData(
    @DrawableRes val res: Int,
    val tint: Color,
    val description: String,
)
