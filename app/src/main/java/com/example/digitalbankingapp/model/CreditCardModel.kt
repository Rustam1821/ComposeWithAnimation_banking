package com.example.digitalbankingapp.model

import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.utils.CardIssuerFinder
import com.example.digitalbankingapp.utils.DECIMAL_FORMAT_PATTERN
import com.example.digitalbankingapp.utils.EMPTY_STRING
import java.text.DecimalFormat

data class CreditCardModel(
    var number: String = EMPTY_STRING,
    var expiration: String = EMPTY_STRING,
    var holderName: String = EMPTY_STRING,
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
        val dec = DecimalFormat(DECIMAL_FORMAT_PATTERN)
        return dec.format(number)
    }
}