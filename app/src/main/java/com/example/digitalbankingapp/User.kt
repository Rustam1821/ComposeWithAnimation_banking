package com.example.digitalbankingapp

data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val sex: String,
    val description: String,
    val userImageId: Int = 0,
) {
    companion object {
        val DEFAULT_USER = User(
            id = 123456,
            name = "George Washington",
            age = 52,
            sex = "male",
            description = "An American military officer, statesman, and Founding Father who served as the 1st president of the United States from 1789 to 1797.",
            userImageId = R.drawable.washington
        )
    }
}
