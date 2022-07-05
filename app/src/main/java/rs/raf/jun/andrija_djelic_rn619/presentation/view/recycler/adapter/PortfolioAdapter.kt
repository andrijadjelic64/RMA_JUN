package rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity
import rs.raf.jun.andrija_djelic_rn619.databinding.LayoutItemOwnedStockBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.diff.PortfolioDiffCallback
import rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.viewholder.PortfolioViewHolder

class PortfolioAdapter (
    val openStockTicket: (stockEntity: StockEntity) -> Unit
): ListAdapter<StockEntity, PortfolioViewHolder>(PortfolioDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val itemBinding = LayoutItemOwnedStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PortfolioViewHolder(itemBinding) { openStockTicket(getItem(it)) }
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}