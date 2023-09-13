package com.example.datalist.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.datalist.R

val MontSerratBold = FontFamily(
    Font(R.font.montserrat_bold)
)

val MontSerratRegular = FontFamily(
    Font(R.font.montserrat_regular)
)

val RobotoMonoBold = FontFamily(
    Font(R.font.robotomono_bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = MontSerratRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = RobotoMonoBold,
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MontSerratBold,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )
)