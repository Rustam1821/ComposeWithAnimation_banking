package com.example.digitalbankingapp.model

data class TransactionModel(
    val iconId: Int,
    val description: String,
    val date: String,
    private val price: Int,
) {
    val formattedPrice: String = "-$price $"
}