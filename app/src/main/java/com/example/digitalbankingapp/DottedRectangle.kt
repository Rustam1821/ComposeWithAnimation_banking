package com.example.digitalbankingapp

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AddNewCardBox() {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
    )
    val color = MaterialTheme.colors.onBackground
    Box(
        modifier = Modifier
            .size(50.dp, 260.dp)
            .padding(
                10.dp
            )
            .clickable { Log.e("--->", "Do you want to add new Card?") },
        contentAlignment = Alignment.Center
    )
    {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawRoundRect(
                color = color,
                cornerRadius = CornerRadius(
                    x = 30f,
                    y = 30f
                ),
                style = stroke,

                )
        }
        Text(
            textAlign = TextAlign.Center, text = "+"
        )
    }
}
