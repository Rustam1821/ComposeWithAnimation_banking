package com.example.digitalbankingapp.view.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.digitalbankingapp.AddNewCardBox
import com.example.digitalbankingapp.HomeAppBar
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.data.transactionsData
import com.example.digitalbankingapp.model.TransactionModel
import com.example.digitalbankingapp.ui.theme.DarkGray
import com.example.digitalbankingapp.ui.theme.Gray90
import com.example.digitalbankingapp.ui.theme.Gray98
import com.example.digitalbankingapp.view.MenuItem
import com.example.digitalbankingapp.view.MenuItemButton
import com.example.digitalbankingapp.view.TwoCards
import kotlinx.coroutines.launch
import java.lang.Float.min

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val closeDrawer: () -> Unit = { scope.launch { scaffoldState.drawerState.close() } }
    Scaffold(
        topBar = {
            HomeAppBar(
                modifier = modifier,
                scope = scope,
                scaffoldState = scaffoldState,
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            BankDrawer(
                closeDrawer = closeDrawer
            )
        },
        drawerShape = RectangleShape,
        drawerGesturesEnabled = false,
    ) {
        HomeScreenContent()
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
) {

    val scrollState = rememberLazyListState()
    val scrollOffset: Float = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 100f + scrollState.firstVisibleItemIndex)
    )
    val tableSize by animateDpAsState(targetValue = max(0.dp, 322.dp * scrollOffset))

    Column(
        modifier = modifier.background(MaterialTheme.colors.background)
    ) {
        HomeScreenCollapsingPart(modifier = modifier, tableSize = tableSize)
        HomeScreenScrollablePart(modifier = modifier, scrollState = scrollState)
    }
}

@Composable
private fun HomeScreenCollapsingPart(
    modifier: Modifier = Modifier,
    tableSize: Dp
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(tableSize)
            .fillMaxWidth(),
    ) {
        Row {
            AddNewCardBox(modifier = modifier)
            TwoCards(modifier = modifier)
        }
        CentralMenuRow()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenScrollablePart(
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
) {
    val transactions: List<TransactionModel> = transactionsData()
    LazyColumn(
        modifier = modifier
            .padding(vertical = 4.dp)
            .background(MaterialTheme.colors.background),
        state = scrollState
    ) {
        stickyHeader {
            TransactionsHeader(modifier = modifier)
        }
        items(items = transactions) { transaction ->
            TransactionItem(modifier = modifier, transaction = transaction)
        }
    }
}

@Composable
private fun CentralMenuRow(
    modifier: Modifier = Modifier,
) {
    val menuItems = listOf(
        MenuItem.Transfer,
        MenuItem.Payment,
        MenuItem.Shopping,
        MenuItem.More
    )
    Row(
        modifier = modifier
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

@Composable
private fun TransactionsHeader(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .height(8.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
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
            text = stringResource(id = R.string.home_screen_transactions),
        )
        Text(
            fontFamily = fontFamily,
            fontSize = 13.sp,
            color = color,
            text = stringResource(id = R.string.home_screen_view_all),
        )
    }
}

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    transaction: TransactionModel,
) {
    Card(
        backgroundColor = Gray98,
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clip(MaterialTheme.shapes.large),
        elevation = 0.dp
    ) {
        Row(
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                modifier = modifier
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
                modifier = modifier
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