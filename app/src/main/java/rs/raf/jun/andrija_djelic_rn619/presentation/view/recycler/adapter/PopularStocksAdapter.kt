package rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks.PopularStocks
import rs.raf.jun.andrija_djelic_rn619.databinding.LayoutItemPopularStocksBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.diff.PopularStocksDiffCallback
import rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.viewholder.PopularStocksViewHolder

class PopularStocksAdapter(
    val openStockTicket: (popularStocks: PopularStocks) -> Unit
): ListAdapter<PopularStocks, PopularStocksViewHolder>(PopularStocksDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularStocksViewHolder {
        val itemBinding = LayoutItemPopularStocksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularStocksViewHolder(itemBinding) { openStockTicket(getItem(it)) }
    }

    override fun onBindViewHolder(holder: PopularStocksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}