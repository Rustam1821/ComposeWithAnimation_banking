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
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        topBar = { ProfileAppBar(
            modifier = modifier,
            navController = navController,
        ) }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(ArcTransferColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

        ) {
            Icon(
                modifier = modifier.size(80.dp),
                imageVector = Icons.Outlined.Person,
                tint = Color.Black,
                contentDescription = null,
            )
            Text(
                modifier = modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.profile_not_implemented),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )
        }
    }
}