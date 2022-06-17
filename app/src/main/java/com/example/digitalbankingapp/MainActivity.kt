package com.example.digitalbankingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digitalbankingapp.ui.theme.DigitalBankingAppTheme
import com.example.digitalbankingapp.view.MyMenuItem
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
private fun DigitalBanking() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            backgroundColor = Color.White,
            topBar = {
                MyAppBar()
            },
            content = {
                Column {

                    Row(
                        modifier = Modifier.background(
                            color = MaterialTheme.colors.background
                        )
                    ) {
                        AddNewCardBox()
                        TwoCards()
                    }

                    Row(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),


                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MyMenuItem(
                            iconId = R.drawable.ic_menu_transfer,
                            text = "Transfer"
                        )
                        MyMenuItem(
                            iconId = R.drawable.ic_menu_payment,
                            text = "Payment"
                        )
                        MyMenuItem(
                            iconId = R.drawable.ic_menu_shopping,
                            text = "Shopping"
                        )
                        MyMenuItem(
                            iconId = R.drawable.ic_menu_more,
                            text = "More"
                        )
                    }
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