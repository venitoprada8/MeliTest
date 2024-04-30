package com.venanciop.melitest.data.utils

import java.text.NumberFormat
import java.util.*



fun formatPrice(price: Float?, currencyId: String?): String {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    currencyId?.let {
        format.currency = Currency.getInstance(currencyId)
    }
    return format.format(price ?: 0)
}