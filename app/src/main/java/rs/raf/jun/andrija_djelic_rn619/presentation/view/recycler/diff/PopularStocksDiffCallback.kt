package rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks.PopularStocks

class PopularStocksDiffCallback:  DiffUtil.ItemCallback<PopularStocks>() {
    override fun areItemsTheSame(oldItem: PopularStocks, newItem: PopularStocks): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.marketName == newItem.marketName
    }

    override fun areContentsTheSame(oldItem: PopularStocks, newItem: PopularStocks): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.marketName == newItem.marketName
    }
}