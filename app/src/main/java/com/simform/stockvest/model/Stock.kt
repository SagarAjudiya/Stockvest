package com.simform.stockvest.model

import kotlin.random.Random

data class Stock(
    val id: Int = Random.nextInt(from = 0, until = 9999),
    val name: String,
    val companyName: String,
    val price: String,
    val priceChange: String,
    val open: String,
    val high: String,
    val low: String,
    val lot: String,
    val value: String,
    val frequency: String,
    var isFavorite: Boolean = false,
    var category: Category = Category(),
    val periodData: ArrayList<Period> = arrayListOf(),
    val tags: ArrayList<String> = arrayListOf(),
    val netIncome: ArrayList<StockSummary> = arrayListOf(),
    val valuation: ArrayList<StockSummary> = arrayListOf(),
    val solvency: ArrayList<StockSummary> = arrayListOf(),
    val dividend: ArrayList<StockSummary> = arrayListOf(),
) {
    companion object {
        private val netIncome = arrayListOf(
            StockSummary(subTitle = "Market Cap", value = "10.436 B"),
            StockSummary(subTitle = "Current Share Outstanding", value = "15,02 B"),
        )
        private val valuation = arrayListOf(
            StockSummary(subTitle = "Current PE Ratio (Annualised)", value = "6,40"),
            StockSummary(subTitle = "Current PE Ratio (TTM)", value = "6,80"),
            StockSummary(subTitle = "Forward PE Ratio", value = "-"),
            StockSummary(subTitle = "Current Price to Sales (TTM)", value = "1,53"),
            StockSummary(subTitle = "Current Price to Book Value", value = "0,98"),
            StockSummary(subTitle = "Current Price To Cashflow (TTM)", value = "4,82"),
            StockSummary(subTitle = "Current Price To Free Cashflow (TTM)", value = "5,18 "),
        )
        private val solvency = arrayListOf(
            StockSummary(subTitle = "Current Ratio (Quarter)", value = "-"),
            StockSummary(subTitle = "Quick Ratio (Quarter)", value = "-"),
            StockSummary(subTitle = "Debt to Equity Ratio (Quarter)", value = "-"),
        )
        private val dividend = arrayListOf(
            StockSummary(subTitle = "Dividend", value = "52,11"),
            StockSummary(subTitle = "Payout Ratio", value = "51,37%"),
            StockSummary(subTitle = "Dividend Yield", value = "7.,50%"),
            StockSummary(subTitle = "Latest Dividend Ex-Date", value = "28 Mar 22"),
        )
        private val tags = arrayListOf(
            "Bank",
            "ESGQKEHATI",
            "IDXSMC-COM",
            "INFOBANK15",
        )
        private val periods = arrayListOf(
            Period(
                name = "PERIOD",
                thirdYear = "2022",
                secondYear = "2021",
                firstYear = "2020",
            ),
            Period(
                name = "Q1",
                thirdYear = "454 B",
                secondYear = "448 B",
                firstYear = "439 B",
            ),
            Period(
                name = "Q2",
                thirdYear = "362 B",
                secondYear = "355 B",
                firstYear = "331 B",
            ),
            Period(
                name = "Q3",
                thirdYear = "-",
                secondYear = "46 B",
                firstYear = "329 B",
            ),
            Period(
                name = "Q4",
                thirdYear = "-",
                secondYear = "382 B",
                firstYear = "389 B",
            ),
            Period(
                name = "Annual",
                thirdYear = "1.631 B",
                secondYear = "1.523 B",
                firstYear = "1.489 B",
            ),
            Period(
                name = "TTM (Q2)",
                thirdYear = "1.535 B",
                secondYear = "1.522 B",
                firstYear = "1.330 B",
            ),
        )
        val data = arrayListOf(
            Stock(
                name = "IHSG",
                companyName = "IHSG",
                price = "7.056,04",
                priceChange = "-35,72 (-0,50%)",
                open = "7.091,76",
                high = "7.100,81",
                low = "7.016,70",
                lot = "186,26 M",
                value = "9,88 T",
                periodData = periods,
                tags = tags,
                category = Category(isTrending = true, isMostActive = true, isTopGainer = true),
                frequency = "1,10 M",
                netIncome = netIncome,
                valuation = valuation,
                solvency = solvency,
                dividend = dividend,
            ), Stock(
                name = "GOTO",
                companyName = "GoTo Gojek Tokopedia Tbk",
                price = "198",
                priceChange = "+1 (+0,53%)",
                open = "7.091,76",
                high = "7.100,81",
                low = "7.016,70",
                lot = "186,26 M",
                value = "9,88 T",
                frequency = "1,10 M",
                isFavorite = true,
                category = Category(isMostActive = true, isTopGainer = true),
            ), Stock(
                name = "PTBA",
                companyName = "Bukit Asam Tbk",
                price = "3.790",
                priceChange = "-20 (-0,52%)",
                open = "7.091,76",
                high = "7.100,81",
                low = "7.016,70",
                lot = "186,26 M",
                value = "9,88 T",
                frequency = "1,10 M",
                category = Category(isTrending = true, isMostActive = true),
            ), Stock(
                name = "ACES",
                companyName = "Ace Hardware Indonesia Tbk",
                price = "570",
                priceChange = "+35 (+6,54%)",
                open = "7.091,76",
                high = "7.100,81",
                low = "7.016,70",
                lot = "186,26 M",
                value = "9,88 T",
                frequency = "1,10 M",
                isFavorite = true,
                category = Category(isTopLoser = true, isMostActive = true),
            ), Stock(
                name = "ANTM",
                companyName = "Aneka Tambang Tbk",
                price = "1.840",
                priceChange = "+30 (+1,66%)",
                open = "7.091,76",
                high = "7.100,81",
                low = "7.016,70",
                lot = "186,26 M",
                value = "9,88 T",
                frequency = "1,10 M",
                category = Category(isTrending = true, isMostActive = true, isTopGainer = true),
            )
        )
    }
}
