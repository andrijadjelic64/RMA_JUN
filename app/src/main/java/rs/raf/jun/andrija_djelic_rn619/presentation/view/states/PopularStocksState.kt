package rs.raf.jun.andrija_djelic_rn619.presentation.view.states

import rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks.PopularStocks

sealed class PopularStocksState {
    object Loading : PopularStocksState()
    object DataFetched : PopularStocksState()
    data class Success(val list: List<PopularStocks>) : PopularStocksState()
    data class Error(val message: String) : PopularStocksState()
}