package com.example.digitalbankingapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.digitalbankingapp.ui.theme.ArcTransferColor
import com.example.digitalbankingapp.ui.theme.Typography

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
                    contentDescription = null,
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
                        text = stringResource(id = R.string.on_boarding_moto),
                        color = MaterialTheme.colors.background,
                        style = Typography.h4,
                    )
                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        text = stringResource(id = R.string.on_boarding_lure),
                        color = MaterialTheme.colors.onSecondary,
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
                            text = stringResource(id = R.string.on_boarding_btn_text),
                            style = MaterialTheme.typography.button,
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