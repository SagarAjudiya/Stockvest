package com.simform.stockvest.ui.favorites

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simform.stockvest.R
import com.simform.stockvest.adapter.StockAdapter
import com.simform.stockvest.databinding.FragmentFavoritesBinding
import com.simform.stockvest.model.Stock


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private var stockList = ArrayList<Stock>()
    private var stockAdapter: StockAdapter? = null

    private val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        private var swipeBack = false
        private val buttonWidth = 300f

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            stockList[position].isFavorite = false
            stockList.remove(stockList[position])
            stockAdapter?.submitList(stockList)
        }

        override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
            if (swipeBack) {
                swipeBack = false
                return 0
            }
            return super.convertToAbsoluteDirection(flags, layoutDirection)
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            if (actionState == ACTION_STATE_SWIPE) {
                setTouchListener(
                    c, recyclerView, viewHolder, dY, actionState, isCurrentlyActive
                )
            }
            ColorDrawable().apply {
                color = ResourcesCompat.getColor(resources, R.color.textRed, context?.theme)
                val itemView = viewHolder.itemView
                setBounds(
                    itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom
                )
                draw(c)
            }
            drawButtons(c, viewHolder)
            super.onChildDraw(
                c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive
            )
        }

        @SuppressLint("ClickableViewAccessibility")
        private fun setTouchListener(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            recyclerView.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    super.onChildDraw(
                        c, recyclerView, viewHolder, 0f, dY, actionState, isCurrentlyActive
                    )
                    recyclerView.setOnTouchListener { _, _ -> false }
                    setItemsClickable(recyclerView)
                    swipeBack = false
                }
                return@setOnTouchListener false
            }
        }

        private fun setItemsClickable(
            recyclerView: RecyclerView, isClickable: Boolean = true
        ) {
            for (i in 0 until recyclerView.childCount) {
                recyclerView.getChildAt(i).isClickable = isClickable
            }
        }

        private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {
            val buttonWidthWithoutPadding = buttonWidth - 20
            val corners = 16f
            val itemView = viewHolder.itemView
            val p = Paint()
            val rightButton = RectF(
                itemView.right - buttonWidthWithoutPadding,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )
            p.color = Color.RED
            c.drawRoundRect(rightButton, corners, corners, p)
            drawText(getString(R.string.delete), c, rightButton, p)
        }

        private fun drawText(text: String, c: Canvas, button: RectF, paint: Paint) {
            val textSize = 60f
            paint.apply {
                color = Color.WHITE
                isAntiAlias = true
                this.textSize = textSize
                val textWidth = measureText(text)
                c.drawText(
                    text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2, paint
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        stockAdapter = StockAdapter()
        binding.rvTransaction.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stockAdapter
            val itemTouchHelper = ItemTouchHelper(simpleCallback)
            itemTouchHelper.attachToRecyclerView(this)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        stockList = Stock.data.filter { it.isFavorite } as ArrayList<Stock>
        stockAdapter?.submitList(stockList)
    }

}