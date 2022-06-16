package com.example.digitalbankingapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.digitalbankingapp.R

private val JakartaFonts = FontFamily(
    Font(R.font.plus_jakarta_sans),
    Font(R.font.plus_jakarta_sans_italic, style = FontStyle.Italic),
    Font(R.font.plus_jakarta_sans_light, weight = FontWeight.Light),
    Font(R.font.plus_jakarta_sans_bold, weight = FontWeight.Bold),

)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    subtitle1 = TextStyle(
        fontFamily = JakartaFonts,
        fontSize = 12.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
