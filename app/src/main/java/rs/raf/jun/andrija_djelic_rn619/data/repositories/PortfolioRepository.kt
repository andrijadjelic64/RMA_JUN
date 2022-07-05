package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.*

interface PortfolioRepository {

    fun getPortfolioById(id: Long): Observable<PortfolioWithStocks>

    fun insert(portfolioEntity: PortfolioEntity): Completable

    fun insertStock(stockEntity: StockEntity): Completable

    fun deleteAllStocks(id: Long): Completable

    fun insertOrAddQuantity(item:StockEntity, payingPrice: Double): Completable

    fun deleteOrSubtractQuantity(item:StockEntity, sellingPrice: Double): Completable

    fun getStockByUserIdAndSymbol(userId: Long, symbol: String): Observable<StockEntity>
}