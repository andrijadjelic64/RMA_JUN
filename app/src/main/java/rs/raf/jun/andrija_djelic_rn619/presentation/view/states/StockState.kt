package rs.raf.jun.andrija_djelic_rn619.presentation.view.states

import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity


sealed class StockState{
    data class Success(val stock: StockEntity): StockState()
    data class Error(val message: String): StockState()
}
