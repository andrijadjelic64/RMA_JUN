package rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity

class PortfolioDiffCallback:  DiffUtil.ItemCallback<StockEntity>() {

    override fun areItemsTheSame(oldItem: StockEntity, newItem: StockEntity): Boolean {
        return oldItem.symbol == newItem.symbol &&
                oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: StockEntity, newItem: StockEntity): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.symbol == newItem.symbol
    }
}