package com.example.digitalbankingapp.utils

class CardNumberSplitter(
    private val number: String,
) {
    var first = ""
    var second = ""
    var third = ""
    var fourth = ""

    init {
        splitCardNumber()
    }

    private fun splitCardNumber() {
        first = getBlock(FIRST)
        second = getBlock(SECOND)
        third = getBlock(THIRD)
        fourth = getBlock(FOURTH)
    }

    private fun getBlock(blockNumber: Int): String {
        val start = (blockNumber - 1) * BLOCK_SIZE
        val end = blockNumber * BLOCK_SIZE
        return number.substring(start, end)
    }

    companion object {
        private const val BLOCK_SIZE = 4
        private const val FIRST = 1
        private const val SECOND = 2
        private const val THIRD = 3
        private const val FOURTH = 4
    }

}
