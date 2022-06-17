package com.example.digitalbankingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.digitalbankingapp.model.CreditCardModel
import com.example.digitalbankingapp.ui.theme.DigitalBankingAppTheme
import com.example.digitalbankingapp.view.CreditCard
import com.example.digitalbankingapp.view.TwoCards

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
private fun DigitalBanking(){
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            backgroundColor = Color.White,
            topBar = {
                MyAppBar()
            },
            content = {
                Row (
                    modifier = Modifier.background(
                        color = MaterialTheme.colors.background
                    )
                        ) {
                    AddNewCardBox()
                    TwoCards()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DigitalBanking()
}