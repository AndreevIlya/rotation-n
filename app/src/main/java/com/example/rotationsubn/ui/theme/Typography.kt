package com.example.rotationsubn.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rotationsubn.R

@Immutable
class Typography(
    val display: TypographyType,
    val headline: TypographyType,
    val title: TypographyType,
    val body: TypographyType,
    val label: TypographyType
)

class TypographyType(
    val lg: TextStyle,
    val md: TextStyle,
    val sm: TextStyle
)

val interFontFamily = FontFamily(
    Font(R.font.inter_black),
    Font(R.font.inter_extra_bold),
    Font(R.font.inter_bold),
    Font(R.font.inter_semi_bold),
    Font(R.font.inter_medium),
    Font(R.font.inter_regular),
    Font(R.font.inter_light),
    Font(R.font.inter_extra_light),
    Font(R.font.inter_thin),
)

val typography = Typography(
    display = TypographyType(
        lg = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Black,
            fontSize = 57.sp,
            lineHeight = 69.sp,
            letterSpacing = 0.sp,
        ),
        md = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Black,
            fontSize = 45.sp,
            lineHeight = 55.sp,
            letterSpacing = 0.sp,
        ),
        sm = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Black,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp,
        )
    ),
    headline = TypographyType(
        lg = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
        ),
        md = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            letterSpacing = 0.sp,
        ),
        sm = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            lineHeight = 29.sp,
            letterSpacing = 0.sp,
        )
    ),
    title = TypographyType(
        lg = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 27.sp,
            letterSpacing = 0.sp,
        ),
        md = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.1.sp,
        ),
        sm = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 17.sp,
            letterSpacing = 0.1.sp,
        )
    ),
    body = TypographyType(
        lg = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.2.sp,
        ),
        md = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 17.sp,
            letterSpacing = 0.2.sp,
        ),
        sm = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.2.sp,
        )
    ),
    label = TypographyType(
        lg = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 12.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.1.sp,
        ),
        md = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.1.sp,
        ),
        sm = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 8.sp,
            lineHeight = 10.sp,
            letterSpacing = 0.1.sp,
        )
    )
)
