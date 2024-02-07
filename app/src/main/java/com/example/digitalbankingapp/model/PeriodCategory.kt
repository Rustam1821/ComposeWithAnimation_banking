package com.example.digitalbankingapp.model

import com.example.digitalbankingapp.R.string.*

enum class PeriodCategory(val value: Int) {
    TODAY(value = today),
    THIS_WEEK(value = this_week),
    THIS_MONTH(value = this_month),
    THREE_MONTHS(value = three_months),
    SIX_MONTHS(value = six_months),
    THIS_YEAR(value = this_year),
}
