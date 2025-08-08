package com.safi.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val charcoal = Color(0xFF364156)
private val prussian = Color(0xFF212D40)
private val rich = Color(0xFF11151C)
private val jasper = Color(0xffe8998e) //0xFFD66853
private val gray = Color(0xFFD9D9D9)
private val rose = Color(0xFF7D4E57)

private val LightColorPalette = lightColors(
    primary = charcoal,         // Default UI Color
    background = prussian,      // Default app BG color
    primaryVariant = rich,      // Default Top Bar Color
    onPrimary = jasper,         // Default Text on Primary
    secondary = gray,           // Text Box Color
    onSecondary = rose          // Rose Text on Gray
)

object ThemeController {
    val DarkColorPalette = darkColors(
        primary = charcoal,         // Default UI Color
        background = prussian,      // Default app BG color
        primaryVariant = rich,      // Default Top Bar Color
        onPrimary = jasper,         // Default Text on Primary
        secondary = gray,           // Text Box Color
        onSecondary = rose          // Rose Text on Gray
    )
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colors = ThemeController.DarkColorPalette

    MaterialTheme(
        colors = colors,
        shapes = Shapes(),
        content = content
    )
}