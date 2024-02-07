package com.example.digitalbankingapp.utils

import com.example.digitalbankingapp.model.CardIssuer

class CardIssuerFinder {

    companion object {
        fun findCardIssuer(number: String): CardIssuer =
            when {
                isVisa(number) -> CardIssuer.VISA
                isMasterCard(number) -> CardIssuer.MASTERCARD
                else -> CardIssuer.OTHER
            }

        private fun isVisa(number: String) = number.first() == '4'
        private fun isMasterCard(number: String) = number.substring(0, 2).toInt() in 51 until 56
    }
}