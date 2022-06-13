package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.digitalbankingapp.NavigationItem
import com.example.digitalbankingapp.R

private val drawerItems = listOf(
    NavigationItem.Profile,
    NavigationItem.Home,
    NavigationItem.Transactions,
    NavigationItem.Question,
)

@Composable
fun BankDrawer(
    modifier: Modifier = Modifier,
    closeDrawer: () -> Unit,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 32.dp)
    ) {

        Icon(
            modifier = modifier.clickable(
                onClick = closeDrawer
            ),
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
        )

        Image(
            painter = painterResource(id = R.drawable.ic_credit_cards),
            contentDescription = null
        )

        drawerItems.forEach { screen ->
            Spacer(modifier = modifier.height(16.dp))
            Row {
                Icon(screen.icon_filled, null)
                Spacer(modifier = modifier.width(16.dp))
                Text(
                    text = screen.title,
                    style = MaterialTheme.typography.button
                )

            }
        }
    }
}