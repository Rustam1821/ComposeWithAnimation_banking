package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.data.transactionsData
import com.example.digitalbankingapp.model.TransactionModel
import com.example.digitalbankingapp.ui.theme.*

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
    transactions: List<TransactionModel> = transactionsData()
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
fun TransactionItem(transaction: TransactionModel) {
    Card(
        backgroundColor = Gray98,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .drawBehind {
                        drawCircle(
                            color = Gray90,
                            radius = this.size.maxDimension * 0.5f
                        )
                    },
                painter = painterResource(id = transaction.iconId),
                tint = Color.Black,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = transaction.description,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
                    fontSize = 14.sp,
                )
                Text(
                    text = transaction.date,
                    color = DarkGray,
                    fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                    fontSize = 12.sp
                )
            }
            Text(
                text = transaction.formattedPrice,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
                fontSize = 16.sp
            )
        }

    }
}