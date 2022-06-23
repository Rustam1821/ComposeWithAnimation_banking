package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.ui.theme.Gray90
import com.example.digitalbankingapp.ui.theme.Gray98

@Composable
fun Transactions(
) {
    TransactionsHeader()
    TransactionItems()
}

@Composable
fun TransactionsHeader() {
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ) {
        val fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold))
        val color = MaterialTheme.colors.onBackground

        Text(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = color,
            text = "Transactions",
        )
        Text(
            fontFamily = fontFamily,
            fontSize = 13.sp,
            color = color,
            text = "View All"
        )
    }
}

@Composable
fun TransactionItems(
    transactions: List<String> = List(1000) { "$it" }
) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = transactions) { transaction ->
            TransactionItem(transaction)
        }
    }
}

@Composable
fun TransactionItem(transaction: String) {
    Card(
        backgroundColor = Gray98,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            val textColor = Color.Black
            val fontFamily = FontFamily(Font(R.font.plus_jakarta_sans))
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .drawBehind {
                        drawCircle(
                            color = Gray90,
                            radius = this.size.maxDimension * 0.5f
                        )
                    },
                painter = painterResource(id = R.drawable.ic_transactions_dribbble),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "Subscription",
                    color = Color.Black)
                Text(text = "today at 4:30 pm", color = Color.Black)
            }
            Text(text = "-64 $", color = textColor)
        }

    }
}