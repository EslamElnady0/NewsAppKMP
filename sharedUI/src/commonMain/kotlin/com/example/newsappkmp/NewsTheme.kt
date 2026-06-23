package com.example.newsappkmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal val NewsInk = Color(0xFF101828)
internal val NewsMutedInk = Color(0xFF667085)
internal val NewsCanvas = Color(0xFFF5F7FA)
internal val NewsAccent = Color(0xFFFF6B4A)

@Composable
internal fun NewsAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = NewsAccent,
            surface = Color.White,
            background = NewsCanvas,
            onSurface = NewsInk,
            onBackground = NewsInk,
        ),
        content = content,
    )
}
