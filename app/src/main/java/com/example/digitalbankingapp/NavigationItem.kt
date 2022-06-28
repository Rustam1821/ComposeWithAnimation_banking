package com.example.digitalbankingapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.LiveHelp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.LiveHelp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val icon_outlined: ImageVector,
    val icon_filled: ImageVector,
) {
    object Home : NavigationItem(
        route = "Home",
        icon_outlined = Icons.Outlined.Home,
        icon_filled = Icons.Filled.Home,

    )

    object Transactions : NavigationItem(
        route = "Transactions",
        icon_outlined = Icons.Outlined.Leaderboard,
        icon_filled = Icons.Filled.Leaderboard,
    )

    object Question : NavigationItem(
        route = "Question",
        icon_outlined = Icons.Outlined.LiveHelp,
        icon_filled = Icons.Filled.LiveHelp,
    )

    object Profile : NavigationItem(
        route = "Profile",
        icon_outlined = Icons.Outlined.Person,
        icon_filled = Icons.Filled.Person,
    )
}
