package com.example.digitalbankingapp.model

import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.utils.CardIssuerFinder

data class CreditCardModel(
    var number: String = "",
    var expiration: String = "0000",
    var holderName: String = "",
    var cardEntity: String = "VISA"
) {
    val logoCardIssuer = when (CardIssuerFinder.findCardIssuer(number)) {
        CardIssuer.VISA -> R.drawable.ic_visa_logo
        CardIssuer.MASTERCARD -> R.drawable.ic_mastercard_logo
        else -> null
    }

    val formattedExpiration =
        "${expiration.substring(0, 2)}/${expiration.substring(2, expiration.length)}"

    val encryptedCardNumber =
        "${number.substring(0, 5)} **** **** ${number.substring(13, number.length)}"
}