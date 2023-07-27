package com.simform.stockvest.model

data class Category(
    var isTrending: Boolean = false,
    var isTopGainer: Boolean = false,
    var isTopLoser: Boolean = false,
    var isMostActive: Boolean = false,
)