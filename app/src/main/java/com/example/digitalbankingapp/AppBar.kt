package com.example.digitalbankingapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Preview
@Composable
fun HomeAppBar() {
    TopAppBar(
        title = {
            Text(text = "My digital bank")
        },
        navigationIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp,
        actions = {
            UserImage(User.DEFAULT_USER)
        }
    )
}

@Composable
fun TransactionsAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = "Transactions History",
                modifier = Modifier
                    .padding(end = 32.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = if (navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else null,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp,
    )
}
