package com.example.digitalbankingapp.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable

@Composable
fun AnimatedLowerCard(
    isVisible: Boolean,
    content: @Composable() AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            animationSpec = tween(delayMillis = 350),
            initialOffsetX = { fullWidth -> -fullWidth }
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth / 2 }
        ),
        content = { content() }
    )
}

@Composable
fun AnimatedUpperCard(
    isVisible: Boolean,
    content: @Composable() AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            animationSpec = tween(delayMillis = 350),
            initialOffsetX = { fullWidth -> fullWidth * 2 }
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth / 2 }
        ),
        content = { content() }
    )
}
