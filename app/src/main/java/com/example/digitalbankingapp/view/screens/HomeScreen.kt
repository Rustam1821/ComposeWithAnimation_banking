package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.digitalbankingapp.AddNewCardBox
import com.example.digitalbankingapp.HomeAppBar
import com.example.digitalbankingapp.view.MenuItem
import com.example.digitalbankingapp.view.MenuItemButton
import com.example.digitalbankingapp.view.TwoCards
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val closeDrawer: () -> Unit = { scope.launch { scaffoldState.drawerState.close() } }
    Scaffold(
        topBar = { HomeAppBar(scope, scaffoldState) },
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
private fun HomeScreenContent() {
    val menuItems = listOf(
        MenuItem.Transfer,
        MenuItem.Payment,
        MenuItem.Shopping,
        MenuItem.More
    )
    Column(
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {

        Row {
            AddNewCardBox()
            TwoCards()
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
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
        Transactions()
    }
}