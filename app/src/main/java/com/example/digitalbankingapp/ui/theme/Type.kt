package com.example.digitalbankingapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.digitalbankingapp.R

//private val JakartaFonts = FontFamily(
//    Font(R.font.plus_jakarta_sans_bold),
//    Font(R.font.plus_jakarta_sans_bold_italic),
//    Font(R.font.plus_jakarta_sans_extra_bold),
//    Font(R.font.plus_jakarta_sans_extra_bold_italic),
//    Font(R.font.plus_jakarta_sans_extra_light),
//    Font(R.font.plus_jakarta_sans_extra_light_italic),
//    Font(R.font.plus_jakarta_sans_italic),
//    Font(R.font.plus_jakarta_sans_light),
//    Font(R.font.plus_jakarta_sans_light_italic),
//    Font(R.font.plus_jakarta_sans_medium),
//    Font(R.font.plus_jakarta_sans_medium_italic),
//    Font(R.font.plus_jakarta_sans_regular),
//    Font(R.font.plus_jakarta_sans_semi_bold),
//    Font(R.font.plus_jakarta_sans_semi_bold_italic),
//)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
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
