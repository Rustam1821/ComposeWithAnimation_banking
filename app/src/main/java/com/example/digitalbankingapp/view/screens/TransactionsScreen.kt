package com.example.digitalbankingapp.view.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.TransactionsAppBar
import com.example.digitalbankingapp.data.balanceData
import com.example.digitalbankingapp.data.transactionsData
import com.example.digitalbankingapp.model.BalanceModel
import com.example.digitalbankingapp.model.PeriodCategory
import com.example.digitalbankingapp.model.TransactionModel
import com.example.digitalbankingapp.ui.theme.DarkGray
import com.example.digitalbankingapp.view.formattedBalance
import java.lang.Float.min

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TransactionsAppBar(
                navController = navController,
                modifier = modifier
            )
        }
    ) {
        TransactionScreenContent(modifier = modifier)
    }
}

@Composable
private fun TransactionScreenContent(
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 100f + scrollState.firstVisibleItemIndex)
    )
    val tableSize by animateDpAsState(targetValue = max(0.dp, 380.dp * scrollOffset))

    TransactionsScreenCollapsingPart(modifier = modifier, tableSize = tableSize)
    Column(
    ) {
        Spacer(modifier = modifier.height(tableSize / 1.5f))
        TransactionsScreenScrollablePart(modifier = modifier, scrollState = scrollState)
    }
}

@Composable
private fun TransactionsScreenCollapsingPart(
    modifier: Modifier = Modifier,
    tableSize: Dp,
) {
    val data = balanceData()
    Box(
        modifier = modifier
            .height(tableSize)
            .fillMaxWidth()
    ) {
        SharesArc(balanceData = data)
        Column {
            DisplayBalance(
                modifier = modifier,
                amount = formattedBalance(data.sumOf { it.balance })
            )
            Spacer(modifier = modifier.height(48.dp))
            DisplayLegend(modifier = modifier, data = data)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TransactionsScreenScrollablePart(
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
) {
    val transactions: List<TransactionModel> = transactionsData()
    Box(
        modifier = modifier.padding(top = 0.dp)
    ) {
        LazyColumn(
            modifier = modifier
                .padding(vertical = 4.dp)
                .background(MaterialTheme.colors.background),
            state = scrollState
        ) {
            stickyHeader {
                PeriodCategoryRow(onPeriodSelected = {})
            }
            items(items = transactions) { transaction ->
                TransactionItem(modifier = modifier, transaction = transaction)
            }
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
            .graphicsLayer {
                shape = CircleShape
                clip = true
            }
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
fun DisplayBalance(
    modifier: Modifier = Modifier,
    amount: String
) {
    Column(
        modifier = modifier
            .padding(top = 128.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.transactions_balance),
            fontFamily = FontFamily(
                Font(R.font.plus_jakarta_sans)
            ),
            color = MaterialTheme.colors.secondary,
            fontSize = 14.sp
        )
        Text(
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
            text = stringResource(id = R.string.transactions_amount, amount),
            fontSize = 14.sp,
            color = MaterialTheme.colors.onBackground,
        )
    }
}

@Composable
fun DisplayLegend(
    modifier: Modifier = Modifier,
    data: List<BalanceModel>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in data.indices) {
            DisplayLegendItem(
                modifier = modifier,
                color = data[i].color,
                legend = stringResource(id = data[i].description)
            )
        }
    }
}

@Composable
fun DisplayLegendItem(
    modifier: Modifier = Modifier,
    color: Color,
    legend: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .size(10.dp)
                .background(color = color, shape = CircleShape)
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(text = legend)
    }

}

@Composable
private fun PeriodCategoryRow(
    modifier: Modifier = Modifier,
    onPeriodSelected: (PeriodCategory) -> Unit,
) {
    val periodCategories = PeriodCategory.values()
    var selectedTab = remember { mutableStateOf(0) }
    Column(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 8.dp)
    ) {
        Text(
            modifier = modifier.padding(horizontal = 8.dp),
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground,
            text = stringResource(id = R.string.transactions_details),
        )

        Spacer(modifier = modifier.height(8.dp))

        ScrollableTabRow(
            selectedTabIndex = selectedTab.value,
            edgePadding = 0.dp,
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
                        modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                        text = stringResource(id = periodItem.value),
                        isSelected = index == selectedTab.value,
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(8.dp))
    }
}

@Composable
private fun ChoicePeriodChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
) {
    Surface(
        color = if (isSelected) MaterialTheme.colors.onBackground else MaterialTheme.colors.background,
        shape = MaterialTheme.shapes.medium,
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
    TransactionScreenContent()
}