package com.example.digitalbankingapp

sealed class NavigationItem(
    var route: String,
    var icon: Int,
) {
    object Home : NavigationItem(route = "home", icon = R.drawable.ic_botbar_home)
    object Transactions :
        NavigationItem(route = "transactions", icon = R.drawable.ic_botbar_transaction_wh)

    object Question : NavigationItem(route = "question", icon = R.drawable.ic_botbar_question_wh)
    object Profile : NavigationItem(route = "profile", icon = R.drawable.ic_botbar_profile_wh)
}
