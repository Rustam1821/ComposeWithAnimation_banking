package com.example.digitalbankingapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun UserImage(user: User) {
    Image(
        painter = painterResource(id = user.userImageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clickable(
                indication = rememberRipple(
                    bounded = true,
                    radius = 26.dp,
                    color = MaterialTheme.colors.onBackground
                ),
                interactionSource = remember { MutableInteractionSource() },
                onClick = { Log.e("--->", "image was clicked") }
            )
            .padding(8.dp)
            .size(34.dp)
            .clip(shape = RoundedCornerShape(16.dp)),
    )
}