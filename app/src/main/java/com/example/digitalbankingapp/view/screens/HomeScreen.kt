package com.example.digitalbankingapp.view.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.RectangleShape
import com.example.digitalbankingapp.HomeAppBar
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