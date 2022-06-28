package com.example.digitalbankingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digitalbankingapp.ui.theme.ArcTransferColor
import com.example.digitalbankingapp.ui.theme.Gray90

@Composable
fun OnBoardingScreen(
    onClick: () -> Unit
) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.onBackground)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.starting_screen),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 160.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Easy banking, easy life",
                        color = MaterialTheme.colors.background,
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
                    )
                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        text = "Friendly digital banking to make you feel better to manage your financial matter!",
                        color = Gray90,
                        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                    )
                    Button(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = ArcTransferColor),
                        onClick = onClick,
                    ) {
                        Text(
                            text = "Get Started",
                            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
                            color = MaterialTheme.colors.onBackground,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    OnBoardingScreen({})
}