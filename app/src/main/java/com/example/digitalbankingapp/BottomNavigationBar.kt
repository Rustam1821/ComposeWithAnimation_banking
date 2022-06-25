package com.example.digitalbankingapp

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.digitalbankingapp.view.screens.HomeScreen
import com.example.digitalbankingapp.view.screens.ProfileScreen
import com.example.digitalbankingapp.view.screens.QuestionScreen
import com.example.digitalbankingapp.view.screens.TransactionsScreen

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Transactions,
        NavigationItem.Question,
        NavigationItem.Profile
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 10.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } ?: false
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = if (selected) item.icon_filled else item.icon_outlined,
                        contentDescription = null
                    )
                },
                selectedContentColor = MaterialTheme.colors.onBackground,
                unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                alwaysShowLabel = true,
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(route = item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Transactions.route) {
            TransactionsScreen(navController)
        }
        composable(NavigationItem.Question.route) {
            QuestionScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
    }
}