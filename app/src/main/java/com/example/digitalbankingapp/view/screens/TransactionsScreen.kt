package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.TransactionsAppBar
import com.example.digitalbankingapp.data.balanceData
import com.example.digitalbankingapp.model.BalanceModel
import com.example.digitalbankingapp.ui.theme.DarkGray
import com.example.digitalbankingapp.view.formattedBalance

@Composable
fun TransactionsScreen(
    navController: NavController
) {
    val data = balanceData()

    Scaffold(
        topBar = { TransactionsAppBar(navController) }
    ) {
        SharesArc(balanceData = data)
        Column(
        ) {
            DisplayBalance(formattedBalance(data.sumOf { it.balance }))
            Spacer(modifier = Modifier.height(48.dp))
            DisplayLegend(data)
        }
    }
}

@Composable
fun SharesArc(
    modifier: Modifier = Modifier,
    balanceData: List<BalanceModel>,
) {
    Canvas(
        modifier = modifier
            .padding(16.dp)
            .height(380.dp)
            .fillMaxWidth()
    )
    {
        val totalSum = balanceData.sumOf { it.balance }
        val shares = balanceData.map { it.balance * 100 / totalSum }
        val sweepAngles = shares.map { 180 * it.toFloat() / 100 }
        var startAngle = -180f
        val strokeWidth = 35.dp
        val sizeArc = size / 1.2f
        val topLeft = Offset(
            x = (size.width - sizeArc.width) / 2f,
            y = (size.height - sizeArc.height) / 2f
        )
        val stroke = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt)

        for (i in balanceData.indices) {
            drawArc(
                color = balanceData[i].color,
                startAngle = startAngle,
                sweepAngle = sweepAngles[i],
                topLeft = topLeft,
                useCenter = false,
                style = stroke,
                size = sizeArc
            )
            startAngle += sweepAngles[i]
        }


    }
}

@Composable
fun DisplayBalance(amount: String) {
    Column(
        modifier = Modifier
            .padding(top = 128.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Balance",
            fontFamily = FontFamily(
                Font(R.font.plus_jakarta_sans)
            ),
            color = DarkGray,
            fontSize = 14.sp
        )
        Text(
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
            text = "USD $amount",
            fontSize = 14.sp,
            color = MaterialTheme.colors.onBackground,
        )
    }
}

@Composable
fun DisplayLegend(data: List<BalanceModel>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in data.indices) {
            DisplayLegendItem(
                color = data[i].color,
                legend = data[i].description
            )
        }
    }
}

@Composable
fun DisplayLegendItem(color: Color, legend: String) {
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
    SharesArc(
        balanceData = balanceData()
    )
}