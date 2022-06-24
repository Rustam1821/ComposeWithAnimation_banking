package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digitalbankingapp.TransactionsAppBar
import com.example.digitalbankingapp.ui.theme.ArcCommerceColor
import com.example.digitalbankingapp.ui.theme.ArcPaymentColor

@Composable
fun TransactionsScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { TransactionsAppBar(navController) }
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center
        ) {
            TransactionsArc(
                percentage = 0.8f,
                strokeWidth = 30.dp
            )

        }
    }
}

@Composable
fun TransactionsArc(
    modifier: Modifier = Modifier,
    percentage: Float,
    strokeWidth: Dp,
) {
    Canvas(
        modifier = modifier
            .padding(16.dp)
            .size(400.dp),
    )
    {
        val sizeArc = size/1.2f
        //eCommerce line
        drawArc(
            color = ArcCommerceColor,
            startAngle = -180f,
            sweepAngle = 180f,
            topLeft = Offset(
                x = (size.width - sizeArc.width) / 2f,
                y = (size.height - sizeArc.height) / 2f
            ),
            useCenter = false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt),
            size = sizeArc
        )
//        //ePayment line
        drawArc(
            color = ArcPaymentColor,
            startAngle = -180f,
            sweepAngle = 180f * percentage,
            topLeft = Offset(
                x = (size.width - sizeArc.width) / 2f,
                y = (size.height - sizeArc.height) / 2f
            ),
            useCenter = false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt),
            size = sizeArc
        )
    }
}

@Preview
@Composable
fun PreviewArcs() {
    TransactionsArc(
        percentage = 0.8f,
        strokeWidth = 30.dp
    )
}