package com.example.digitalbankingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
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
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
    MainScreen()
    AnimatedVisibility(
        visible = shouldShowOnBoarding,
        exit = fadeOut(
            animationSpec = tween(
                delayMillis = 200,
                durationMillis = 400,
                easing = FastOutLinearInEasing
            )
        )

    ) {
        OnBoardingScreen {
            shouldShowOnBoarding = false
        }
    }
}

@Composable
private fun MainScreen() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
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