package com.example.rotationsubn.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class Gaps(
    val square: GapType<PaddingValues>,
    val row: GapType<PaddingValues>,
    val column: GapType<PaddingValues>,
    val horizontal: GapType<Dp>,
    val vertical: GapType<Dp>
)

class GapType<T>(
    val lg: T,
    val md: T,
    val sm: T
)

private val xsGap = 2.dp
private val smGap = 4.dp
private val mdGap = 8.dp
private val lgGap = 12.dp
private val xlGap = 16.dp
private val xxlGap = 20.dp
private val xxxlGap = 24.dp

val gaps: Gaps = Gaps(
    square = GapType(
        lg = PaddingValues(all = xxlGap),
        md = PaddingValues(all = lgGap),
        sm = PaddingValues(all = smGap)
    ),
    row = GapType(
        lg = PaddingValues(horizontal = xxxlGap, vertical = xlGap),
        md = PaddingValues(horizontal = xlGap, vertical = mdGap),
        sm = PaddingValues(horizontal = mdGap, vertical = xsGap)
    ),
    column = GapType(
        lg = PaddingValues(horizontal = xlGap, vertical = xxxlGap),
        md = PaddingValues(horizontal = mdGap, vertical = xlGap),
        sm = PaddingValues(horizontal = xsGap, vertical = mdGap)
    ),
    horizontal = GapType(
        lg = lgGap,
        md = smGap,
        sm = xsGap
    ),
    vertical = GapType(
        lg = xlGap,
        md = mdGap,
        sm = smGap
    )
)
