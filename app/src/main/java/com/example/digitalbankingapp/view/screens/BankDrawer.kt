package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.digitalbankingapp.NavigationItem
import com.example.digitalbankingapp.R

private val screens = listOf(
    NavigationItem.Profile,
    NavigationItem.Home,
    NavigationItem.Transactions,
    NavigationItem.Question,
)

@Composable
fun BankDrawer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 32.dp, top = 32.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_credit_cards),
            contentDescription = null
        )

        for (screen in screens) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = screen.route,
                style = MaterialTheme.typography.button
            )
        }
    }
}