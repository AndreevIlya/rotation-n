package com.example.rotationsubn.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
class Corners(
    val none: Shape,
    val lg: CornerBasedShape,
    val md: CornerBasedShape,
    val sm: CornerBasedShape,
    val circle: CornerBasedShape
)

val corners = Corners(
    none = RectangleShape,
    lg = RoundedCornerShape(16.dp),
    md = RoundedCornerShape(8.dp),
    sm = RoundedCornerShape(4.dp),
    circle = RoundedCornerShape(50)
)
