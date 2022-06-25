package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
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
        Column {


            Box(modifier = Modifier.padding(16.dp)) {

                TransactionsArc(
                    percentage = 0.8f,
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-30).dp)
                ) {
                    Text(text = "Balance", Modifier.align(Alignment.CenterHorizontally))
                    Text(text = "15 000 usd")
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Legend here")
                }
            }
            Text(text = "15 000 usd")
        }
    }
}

@Composable
fun TransactionsArc(
    modifier: Modifier = Modifier,
    percentage: Float,
) {
    Canvas(
        modifier = modifier
//            .padding(16.dp)
            .height(380.dp)
            .fillMaxWidth()
    )
    {
        val strokeWidth = 35.dp
        val sizeArc = size / 1.2f
        val topLeft = Offset(
            x = (size.width - sizeArc.width) / 2f,
            y = (size.height - sizeArc.height) / 2f
        )
        val stroke = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt)

        //eCommerce line
        drawArc(
            color = ArcCommerceColor,
            startAngle = -180f,
            sweepAngle = 180f,
            topLeft = topLeft,
            useCenter = false,
            style = stroke,
            size = sizeArc
        )
        //ePayment line
        drawArc(
            color = ArcPaymentColor,
            startAngle = -180f,
            sweepAngle = 180f * percentage,
            topLeft = topLeft,
            useCenter = false,
            style = stroke,
            size = sizeArc
        )

    }
}

@Composable
fun DisplayLegend(color: Color, legend: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(color = color, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = legend)
    }

}

@Preview
@Composable
fun PreviewArcs() {
    TransactionsArc(
        percentage = 0.8f,
    )
}