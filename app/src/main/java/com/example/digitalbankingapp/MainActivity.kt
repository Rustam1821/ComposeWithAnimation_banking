package com.example.digitalbankingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalBankingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            MyAppBar()
                        },
                        content = {
                            Row {
                                AddNewCardBox()
                                CreditCard(
                                    model = CreditCardModel(
                                        number = "4234567891234567",
                                        expiration = "1227",
                                        holderName = "John Smith",
                                        cardEntity = "VISA"
                                    ),
                                    backgroundColor = colorResource(id = R.color.soap)
                                )
                            }

                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    Row {
        AddNewCardBox()
        CreditCard(
            model = CreditCardModel(
                number = "4234567891234567",
                expiration = "1227",
                holderName = "John Smith",
                cardEntity = "VISA"
            ),
            backgroundColor = colorResource(id = R.color.soap)
        )
    }
}