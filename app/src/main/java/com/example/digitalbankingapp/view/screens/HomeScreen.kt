package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.digitalbankingapp.AddNewCardBox
import com.example.digitalbankingapp.HomeAppBar
import com.example.digitalbankingapp.view.MenuItem
import com.example.digitalbankingapp.view.MenuItemButton
import com.example.digitalbankingapp.view.TwoCards

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {HomeAppBar()}
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
                    text = menuItem.label,
                    iconId = menuItem.iconId
                )
            }
        }
        Transactions()
    }
}