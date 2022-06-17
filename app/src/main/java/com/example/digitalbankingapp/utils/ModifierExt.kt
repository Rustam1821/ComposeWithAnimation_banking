package com.example.digitalbankingapp.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout


fun Modifier.cardMeasuredHeight() = this.then(
    layout { measurable, constraints ->
        var placeable: Placeable

        measurable.measure(constraints).apply {
            placeable = measurable.measure(
                constraints.copy(
                    minWidth = width,
                    maxWidth = width,
                    minHeight = (width * 0.6).toInt(),
                    maxHeight = (width * 0.6).toInt()
                )
            )
        }

        layout(
            width = placeable.width,
            height = placeable.height,
        ) {
            placeable.place(
                x = 0,
                y = 0,
                zIndex = 0f
            )
        }

    }
)