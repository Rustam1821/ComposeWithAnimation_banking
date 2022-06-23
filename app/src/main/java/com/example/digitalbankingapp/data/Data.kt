package com.example.digitalbankingapp.data

import com.example.digitalbankingapp.model.CreditCardModel

fun creditCardData(): List<CreditCardModel>{
    return listOf(
        CreditCardModel(
            number = "4234567894567894",
            expiration = "0526",
            holderName = "John Smith",
            balance = 2865265.43,
        ),
        CreditCardModel(
            number = "5534567894564987",
            expiration = "1228",
            holderName = "John Smith",
            balance = 65356.15,
        )
    )
}