package com.example.digitalbankingapp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digitalbankingapp.ProfileAppBar
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.ui.theme.ArcTransferColor

@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = { ProfileAppBar(navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ArcTransferColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Icon(
                modifier = Modifier.size(80.dp),
                imageVector = Icons.Outlined.Person,
                contentDescription = null,
            )
            Text(
                text = stringResource(id = R.string.profile_not_implemented),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}