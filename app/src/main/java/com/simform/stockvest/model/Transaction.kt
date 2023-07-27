package com.simform.stockvest.model

data class Transaction(
    val name: String,
    val companyName: String,
    val price: String,
    val lot: String,
    val lotPrice: String,
    val investment: String,
    val status: Int,
    val orderTime: String,
)