package com.example.as01pam.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
fun AS01PAMTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = androidx.compose.material3.lightColorScheme(
            primary = Burgundy,
            onPrimary = SoftWhite,

            secondary = DustyBlue,
            onSecondary = DarkBrown,

            background = Cream,
            onBackground = DarkBrown,

            surface = SoftWhite,
            onSurface = DarkBrown,

            outline = MutedBrown
        ),
        typography = Typography(),
        content = content
    )
}