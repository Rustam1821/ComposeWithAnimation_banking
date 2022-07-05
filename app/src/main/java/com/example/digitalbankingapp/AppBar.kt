package com.example.digitalbankingapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeAppBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.home_top_bar))
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch { scaffoldState.drawerState.open() }
                }
            ) {
                Icon(Icons.Filled.Menu, null)
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
                text = stringResource(id = R.string.transactions_top_bar),
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
                        contentDescription = "Back",
                    )
                }
            }
        } else null,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp,
    )
}

@Composable
fun ProfileAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.profile_top_bar),
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
                        contentDescription = "Back",
                    )
                }
            }
        } else null,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp,
    )
}

@Composable
fun QuestionsAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.faq_top_bar),
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
                        contentDescription = "Back",
                    )
                }
            }
        } else null,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp,
    )
}
