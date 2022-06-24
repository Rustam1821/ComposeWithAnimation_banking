package com.example.digitalbankingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.digitalbankingapp.ui.theme.DigitalBankingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalBankingAppTheme {
                DigitalBanking()
            }
        }
    }
}

@Composable
fun DigitalBanking() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnBoardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        MainScreen()
    }
}

@Composable
private fun MainScreen() {
    val navController = rememberNavController()
    var shouldShowBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    shouldShowBottomBar = when (navBackStackEntry?.destination?.route) {
        NavigationItem.Transactions.route -> false
        else -> true
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            topBar = {
                if (shouldShowBottomBar) MyAppBar()
            },
            bottomBar = { BottomNavigationBar(navController) },
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    Navigation(navController)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}