package com.example.digitalbankingapp.data

import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.model.BalanceModel
import com.example.digitalbankingapp.model.CreditCardModel
import com.example.digitalbankingapp.model.TransactionModel
import com.example.digitalbankingapp.ui.theme.ArcCommerceColor
import com.example.digitalbankingapp.ui.theme.ArcPaymentColor
import com.example.digitalbankingapp.ui.theme.ArcTransferColor

fun creditCardData(): List<CreditCardModel> = listOf(
    CreditCardModel(
        number = "4234567894567894",
        expiration = "0526",
        holderName = "John Smith",
        balance = 2865.43,
        backgroundColorId = R.color.aero_blue,
    ),
    CreditCardModel(
        number = "5534567894564987",
        expiration = "1228",
        holderName = "John Smith",
        balance = 65356.15,
        backgroundColorId = R.color.soap
    )
)


fun transactionsData(): List<TransactionModel> {
    val threeElements = listOf(
        TransactionModel(
            iconId = R.drawable.ic_transaction_dribbble,
            description = "Dribbble Subscription",
            date = "Today at 4:30 pm",
            price = 64
        ),
        TransactionModel(
            iconId = R.drawable.ic_transaction_spotify,
            description = "Spotify Monthly",
            date = "Yesterday at 8:12 pm",
            price = 20
        ),
        TransactionModel(
            iconId = R.drawable.ic_transaction_mailchimp,
            description = "Mailchimp Support",
            date = "Wednesday at 1:12 pm",
            price = 35
        ),
    )
    val result = mutableListOf<TransactionModel>()
    for(i in 1..10){
        result.addAll(threeElements)
    }
    return result
}

fun balanceData(): List<BalanceModel> {
    return listOf(
        BalanceModel(
            balance = 20112.03,
            color = ArcTransferColor,
            description = R.string.transactions_arc_transfer,
        ),
        BalanceModel(
            balance = 24351.07,
            color = ArcPaymentColor,
            description = R.string.transactions_arc_payment,
        ),
        BalanceModel(
            balance = 20893.05,
            color = ArcCommerceColor,
            description = R.string.transactions_arc_commerce,
        ),
    )
}