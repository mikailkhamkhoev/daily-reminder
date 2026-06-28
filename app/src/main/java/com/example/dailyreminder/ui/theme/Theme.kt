package com.example.dailyreminder.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val CharmanderColorScheme = lightColorScheme(
    primary = CharmanderOrange,
    onPrimary = CharmanderPureWhite,
    secondary = CharmanderTailBlue,
    onSecondary = CharmanderDarkText,
    background = CharmanderBackground,
    surface = CharmanderFlameYellow
)

@Composable
fun DailyReminderTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CharmanderColorScheme,
        content = content
    )
}
