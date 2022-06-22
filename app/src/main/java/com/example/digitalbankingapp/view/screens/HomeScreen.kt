package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.digitalbankingapp.AddNewCardBox
import com.example.digitalbankingapp.view.MenuItemButton
import com.example.digitalbankingapp.view.MenuItem
import com.example.digitalbankingapp.view.TwoCards

@Composable
fun HomeScreen() {
    val menuItems = listOf(
        MenuItem.Transfer,
        MenuItem.Payment,
        MenuItem.Shopping,
        MenuItem.More
    )
    Column {

        Row(
            modifier = Modifier.background(
                color = MaterialTheme.colors.background
            )
        ) {
            AddNewCardBox()
            TwoCards()
        }

        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
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
    }
}