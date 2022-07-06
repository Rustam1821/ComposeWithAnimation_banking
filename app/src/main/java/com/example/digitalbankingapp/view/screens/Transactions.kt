package com.example.digitalbankingapp.view.screens

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.digitalbankingapp.AddNewCardBox
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.data.transactionsData
import com.example.digitalbankingapp.model.TransactionModel
import com.example.digitalbankingapp.ui.theme.DarkGray
import com.example.digitalbankingapp.ui.theme.Gray90
import com.example.digitalbankingapp.ui.theme.Gray98
import com.example.digitalbankingapp.utils.EMPTY_STRING
import com.example.digitalbankingapp.view.MenuItem
import com.example.digitalbankingapp.view.MenuItemButton
import com.example.digitalbankingapp.view.TwoCards
import java.lang.Float.min


@Composable
fun TransactionsHeader(
    startString: String,
    endString: String = EMPTY_STRING,
//    scrollOffset: Float = 1f,
) {
//    val tableSize by animateDpAsState(targetValue = max(0.dp, 50.dp * scrollOffset))
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .height(tableSize)
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colors.background),
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
            text = startString,
        )
        Text(
            fontFamily = fontFamily,
            fontSize = 13.sp,
            color = color,
            text = endString
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionItems(
//    scrollOffset: Float = 1f,
    transactions: List<TransactionModel> = transactionsData(),
) {
    val menuItems = listOf(
        MenuItem.Transfer,
        MenuItem.Payment,
        MenuItem.Shopping,
        MenuItem.More
    )
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 100f + scrollState.firstVisibleItemIndex)
    )
    var heightInPx by remember { mutableStateOf(IntSize.Zero) }
    val tableSize by animateDpAsState(targetValue = max(0.dp, 322.dp * scrollOffset))
    Column {
        Column (
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(tableSize)
                .onGloballyPositioned {
                    heightInPx = it.size
                    Log.e("--->", "Height in px is $heightInPx")
                }
                .fillMaxWidth(),
                ) {
            Row {
                AddNewCardBox()
                TwoCards()
            }

            Row(
                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .background(Color.Green)
//                    .height(tableSize)

                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                menuItems.forEach { menuItem ->
                    MenuItemButton(
                        text = stringResource(id = menuItem.label),
                        iconId = menuItem.iconId
                    )
                }
            }
        }
        val heightInDp = with(LocalDensity.current) { heightInPx.height.toDp() }
        Log.e("--->", "Height in dp is $heightInDp")
        Log.e("--->", "Height in px is $heightInPx")

        LazyColumn(
            modifier = Modifier.padding(vertical = 4.dp).background(MaterialTheme.colors.background),
            state = scrollState
        ) {
            stickyHeader {
                TransactionsHeader(
                    stringResource(id = R.string.home_screen_transactions),
                    stringResource(id = R.string.home_screen_view_all),
                )
            }
            items(items = transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: TransactionModel) {
    Card(
        backgroundColor = Gray98,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clip(MaterialTheme.shapes.large),
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