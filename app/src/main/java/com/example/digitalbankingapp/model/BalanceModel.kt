package com.example.digitalbankingapp.model

import androidx.compose.ui.graphics.Color

data class BalanceModel(
    val balance: Double,
    val color: Color,
    val description: String,
)