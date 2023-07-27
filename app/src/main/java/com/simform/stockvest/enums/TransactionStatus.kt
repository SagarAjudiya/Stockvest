package com.simform.stockvest.enums

import com.simform.stockvest.R

enum class TransactionStatus(val value: Int) {
    OPEN(0), MATCH(1), WITHDRAWAL(2);

    companion object {
        fun valueOf(value: Int) = TransactionStatus.values().find { it.value == value }
    }

    fun getColor(): Int {
        return when (this) {
            OPEN -> R.color.colorRed
            MATCH -> R.color.textGreen
            WITHDRAWAL -> R.color.textRed
        }
    }

}