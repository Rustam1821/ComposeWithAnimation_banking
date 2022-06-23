package com.example.digitalbankingapp.model

import androidx.compose.ui.graphics.Color
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.utils.CardIssuerFinder
import java.text.DecimalFormat

data class CreditCardModel(
    var number: String = "",
    var expiration: String = "",
    var holderName: String = "",
    var balance: Double = 0.0,
) {
    val logoCardIssuer = when (CardIssuerFinder.findCardIssuer(number)) {
        CardIssuer.VISA -> R.drawable.ic_visa_logo
        CardIssuer.MASTERCARD -> R.drawable.ic_mastercard_logo
        else -> null
    }

    val formattedExpiration =
        "${expiration.substring(0, 2)}/${expiration.substring(2, expiration.length)}"

    val encryptedCardNumber =
        "${number.substring(0, 4)}********${number.substring(12, number.length)}"

    fun formattedBalance(balance: Float): String {
        val dec = DecimalFormat("#,###.00")
        return dec.format(number)
    }
}