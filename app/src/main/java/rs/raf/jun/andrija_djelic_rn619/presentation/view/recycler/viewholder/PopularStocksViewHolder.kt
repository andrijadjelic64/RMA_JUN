package rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.viewholder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks.PopularStocks
import rs.raf.jun.andrija_djelic_rn619.databinding.LayoutItemPopularStocksBinding

class PopularStocksViewHolder(
    private val itembinding: LayoutItemPopularStocksBinding,
    val openStockTicket: (position: Int)-> Unit
): RecyclerView.ViewHolder(itembinding.root) {

    init {
        itembinding.popularStocksTicket.setOnClickListener {
            openStockTicket(layoutPosition)
        }
    }

    fun bind(popularStocks: PopularStocks) {

        val entries: ArrayList<Entry> = ArrayList()
        var i = 0

        itembinding.stockNameLabel.text = popularStocks.name
        itembinding.stockSymbolText.text = popularStocks.symbol
        itembinding.lastPriceLabel.text = popularStocks.last + popularStocks.currency
        itembinding.chartLine.setBackgroundColor(Color.WHITE)
        itembinding.chartLine.description.isEnabled = false
        itembinding.chartLine.setDrawGridBackground(false)
        itembinding.chartLine.setScaleEnabled(true)
        itembinding.chartLine.setPinchZoom(true)
        itembinding.chartLine.isDragEnabled = true

        popularStocks.chart.bars.forEach {
            val value = it.price.toFloat()
            entries.add(Entry(i++.toFloat(), value))
        }


        val lineDataSet = LineDataSet(entries, "")
        if(popularStocks.changeFromPreviousClose.toDouble() > 0){
            lineDataSet.color = Color.GREEN
        } else {
            lineDataSet.color = Color.RED
        }
        val data = LineData(lineDataSet)
        itembinding.chartLine.data = data
        itembinding.chartLine.invalidate()
    }
}