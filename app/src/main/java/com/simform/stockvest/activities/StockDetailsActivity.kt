package com.simform.stockvest.activities

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.simform.stockvest.R
import com.simform.stockvest.adapter.PeriodAdapter
import com.simform.stockvest.adapter.SummaryAdapter
import com.simform.stockvest.databinding.ActivityStockDetailsBinding
import com.simform.stockvest.model.Stock

class StockDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStockDetailsBinding
    private var stockData: Stock? = null

    companion object {
        private const val stockKey = "STOCKLIST"
        fun getIntent(context: Context, stockPosition: Int): Intent =
            Intent(context, StockDetailsActivity::class.java).apply {
                putExtra(stockKey, stockPosition)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stock_details)

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.top_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val ratingMenu = menu?.findItem(R.id.menuRating)
        ratingMenu?.let {
            ratingMenu.isChecked = stockData?.isFavorite ?: false
            ratingMenu.icon = ResourcesCompat.getDrawable(
                resources, if (ratingMenu.isChecked) {
                    R.drawable.menu_rating_filled
                } else {
                    R.drawable.menu_rating
                }, theme
            )
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.menuAlarm -> true
            R.id.menuRating -> {
                item.isChecked = !item.isChecked
                stockData?.isFavorite = item.isChecked
                item.icon = ResourcesCompat.getDrawable(
                    resources, if (item.isChecked) {
                        R.drawable.menu_rating_filled
                    } else {
                        R.drawable.menu_rating
                    }, theme
                )
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        val position = intent.getIntExtra(stockKey, 0)
        stockData = Stock.data.first { it.id == position }
        binding.stock = stockData

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
            setBackgroundDrawable(ColorDrawable(getColor(R.color.white)))
            setHomeAsUpIndicator(R.drawable.icon_back)
        }

        binding.chipGroupTags.apply {
            stockData?.let { stock ->
                stock.tags.forEach {
                    val chip = layoutInflater.inflate(R.layout.chip_tag_layout, this, false) as Chip
                    chip.text = it
                    addView(chip)
                }
            }
        }

        binding.apply {
            rvPeriodic.layoutManager = LinearLayoutManager(this@StockDetailsActivity)
            rvNetIncome.layoutManager = LinearLayoutManager(this@StockDetailsActivity)
            rvValuation.layoutManager = LinearLayoutManager(this@StockDetailsActivity)
            rvSolvency.layoutManager = LinearLayoutManager(this@StockDetailsActivity)
            rvDividend.layoutManager = LinearLayoutManager(this@StockDetailsActivity)
            stockData?.let {
                rvNetIncome.adapter = SummaryAdapter(it.netIncome)
                rvValuation.adapter = SummaryAdapter(it.valuation)
                rvSolvency.adapter = SummaryAdapter(it.solvency)
                rvDividend.adapter = SummaryAdapter(it.dividend)
                rvPeriodic.adapter = PeriodAdapter(it.periodData)
            }
        }
    }

}