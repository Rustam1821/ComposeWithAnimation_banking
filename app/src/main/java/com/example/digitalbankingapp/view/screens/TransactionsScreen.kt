package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.TransactionsAppBar
import com.example.digitalbankingapp.data.balanceData
import com.example.digitalbankingapp.model.BalanceModel
import com.example.digitalbankingapp.model.PeriodCategory
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
        Column {
            DisplayBalance(formattedBalance(data.sumOf { it.balance }))
            Spacer(modifier = Modifier.height(48.dp))
            DisplayLegend(data)
            Spacer(modifier = Modifier.height(24.dp))
            SubsectionHeader("Details")
            Spacer(modifier = Modifier.height(8.dp))
            PeriodCategoryTabs(onPeriodSelected = {})
            TransactionItems()
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
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

@Composable
private fun PeriodCategoryTabs(
    onPeriodSelected: (PeriodCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val periodCategories = PeriodCategory.values()
    var selectedTab = remember { mutableStateOf(0) }
    ScrollableTabRow(
        selectedTabIndex = selectedTab.value,
        edgePadding = 8.dp,
        modifier = modifier,
        divider = {},
        indicator = {},
        backgroundColor = MaterialTheme.colors.background,
    ) {
        periodCategories.forEachIndexed { index, periodItem ->
            Tab(
                selected = selectedTab.value == index,
                onClick = {
                    selectedTab.value = index
                    onPeriodSelected
                }
            ) {
                ChoicePeriodChip(
                    text = periodItem.value,
                    isSelected = index == selectedTab.value,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun ChoicePeriodChip(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = if (isSelected) MaterialTheme.colors.onBackground else MaterialTheme.colors.background,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = DarkGray),
        modifier = modifier.width(100.dp)
    ) {
            Text(
                text = text,
                fontFamily = FontFamily(
                    Font(R.font.plus_jakarta_sans)
                ),
                textAlign = TextAlign.Center,
                color = if (isSelected) MaterialTheme.colors.background else MaterialTheme.colors.onBackground,
                fontSize = 12.sp,
                modifier = modifier.fillMaxWidth()
            )
    }
}

@Preview
@Composable
fun PreviewArcs() {
    SharesArc(
        balanceData = balanceData()
    )
}