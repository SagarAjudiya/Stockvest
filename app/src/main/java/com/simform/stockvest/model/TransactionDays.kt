package com.simform.stockvest.model

data class TransactionDays(
    val day: String, val transactionList: ArrayList<Transaction>
) {
    companion object {
        val transactions = arrayListOf(
            Transaction(
                name = "BJTM",
                companyName = "Bank Jatim",
                price = "Rp680",
                lot = "2 lot",
                lotPrice = "Rp 10.0",
                investment = "Rp 150.00",
                status = 0,
                orderTime = "01 MAR 2021, 10:00:01"
            ),
            Transaction(
                name = "ABTM",
                companyName = "Bank Jatim",
                price = "Rp680",
                lot = "2 lot",
                lotPrice = "Rp 10.0",
                investment = "Rp 150.00",
                status = 1,
                orderTime = "01 MAR 2021, 10:00:01"
            ),
            Transaction(
                name = "JCTM",
                companyName = "Bank Jatim",
                price = "Rp680",
                lot = "2 lot",
                lotPrice = "Rp 10.0",
                investment = "Rp 150.00",
                status = 1,
                orderTime = "01 MAR 2021, 10:00:01"
            ),
            Transaction(
                name = "USTM",
                companyName = "Bank Jatim",
                price = "Rp680",
                lot = "2 lot",
                lotPrice = "Rp 10.0",
                investment = "Rp 150.00",
                status = 2,
                orderTime = "01 MAR 2021, 10:00:01"
            ),
        )
        val data = arrayListOf(
            TransactionDays(day = "Transaction Today", transactionList = transactions),
            TransactionDays(day = "Yesterday", transactionList = transactions),
        )
    }
}