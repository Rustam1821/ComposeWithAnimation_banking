package com.example.digitalbankingapp

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                selectedContentColor = MaterialTheme.colors.onBackground,
                unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
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
        composable(NavigationItem.Home.route){
            HomeScreen()
        }
        composable(NavigationItem.Transactions.route){
            TransactionsScreen()
        }
        composable(NavigationItem.Question.route){
            QuestionScreen()
        }
        composable(NavigationItem.Profile.route){
            ProfileScreen()
        }
    }
}