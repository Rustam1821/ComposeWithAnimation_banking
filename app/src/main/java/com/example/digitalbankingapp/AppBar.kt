package com.example.digitalbankingapp

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job

//@Preview
@Composable
fun MyAppBar() {
    TopAppBar(
        title = {
            Text(text = "My digital bank")
        },
        navigationIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 552.dp,
        actions = {
            UserImage(User.DEFAULT_USER)
        }
    )
}
//{Log.e("--->", "image was clicked")}