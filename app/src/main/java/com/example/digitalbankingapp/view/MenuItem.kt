package com.example.digitalbankingapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digitalbankingapp.R

@Composable
fun MenuItemButton(
    text: String = "text",
    iconId: Int = R.drawable.ic_menu_transfer,
) {
    val bgColor = MaterialTheme.colors.onBackground
    val strokeColor = MaterialTheme.colors.background
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Box(modifier = Modifier.clip(MaterialTheme.shapes.large)) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(64.dp)
                    .clip(MaterialTheme.shapes.small),
            ) {
                Icon(
                    modifier = Modifier
                        .background(bgColor)
                        .padding(16.dp),
                    painter = painterResource(id = iconId),
                    contentDescription = null,
                    tint = strokeColor,
                )
            }

        }
        Text(
            fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
            fontSize = 14.sp,
            color = Color.Gray,
            text = text
        )
    }

}

sealed class MenuItem(
    var route: String = "",
    var label: Int,
    var iconId: Int,
) {
    object Transfer :
        MenuItem(label = R.string.home_menu_item_transfer, iconId = R.drawable.ic_menu_transfer)

    object Payment :
        MenuItem(label = R.string.home_menu_item_payment, iconId = R.drawable.ic_menu_payment)

    object Shopping :
        MenuItem(label = R.string.home_menu_item_shopping, iconId = R.drawable.ic_menu_shopping)

    object More :
        MenuItem(label = R.string.home_menu_item_more, iconId = R.drawable.ic_menu_more)
}


@Preview(showBackground = true, name = "Menu item")
@Composable
fun MenuItemPreview() {
    MenuItemButton()
}
