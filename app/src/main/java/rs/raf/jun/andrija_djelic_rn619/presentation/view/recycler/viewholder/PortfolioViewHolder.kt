package rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.Entry
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity
import rs.raf.jun.andrija_djelic_rn619.databinding.LayoutItemOwnedStockBinding

class PortfolioViewHolder (
    private val itembinding: LayoutItemOwnedStockBinding,
    val openStockTicket: (position: Int)-> Unit
): RecyclerView.ViewHolder(itembinding.root) {

    init {
        itembinding.ownedStockTicket.setOnClickListener {
            openStockTicket(layoutPosition)
        }
    }

    fun bind(stockEntity: StockEntity) {

        val entries: ArrayList<Entry> = ArrayList()
        var i = 0

        itembinding.stockNameLabel.text = stockEntity.name
        itembinding.stockSymbolText.text = stockEntity.symbol
        itembinding.lastPriceLabel.text = stockEntity.last.toString() + stockEntity.currency
        itembinding.quantityText.text = stockEntity.quantity.toString()
    }
}