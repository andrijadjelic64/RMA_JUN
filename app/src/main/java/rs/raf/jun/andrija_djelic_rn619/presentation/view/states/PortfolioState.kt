package rs.raf.jun.andrija_djelic_rn619.presentation.view.states

import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioWithStocks

sealed class PortfolioState{
    object Loading: PortfolioState()
    object DataFetched: PortfolioState()
    data class Success(val portfolio: PortfolioWithStocks): PortfolioState()
    data class Error(val message: String): PortfolioState()
}
