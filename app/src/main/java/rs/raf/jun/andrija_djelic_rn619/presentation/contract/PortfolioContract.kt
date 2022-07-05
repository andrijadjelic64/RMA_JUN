package rs.raf.jun.andrija_djelic_rn619.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioEntity
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PopularStocksState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PortfolioState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.StockState

interface PortfolioContract {
    interface ViewModel{
        val portfolioState: LiveData<PortfolioState>
        val stockState: LiveData<StockState>


        fun getPortfolioById(id:Long)

        fun getStockByUserIdAndSymbol(userId: Long, symbol: String)

        fun insertOrAddQuantity(stockEntity: StockEntity, payingPrice:Double)

        fun deleteOrSubtractQuantity(stockEntity: StockEntity, sellingPrice:Double)

        fun insertPortfolio(portfolioEntity: PortfolioEntity)

        fun insertStock(stockEntity: StockEntity)

        fun deleteAllStocks(id:Long)
    }
}