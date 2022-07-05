package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.datasources.local.PortfolioDao
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioEntity
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioWithStocks
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity

class PortfolioRepositoryImpl(private val localDataSource: PortfolioDao):PortfolioRepository {

    override fun getPortfolioById(id: Long): Observable<PortfolioWithStocks> {
        return localDataSource.getUsersWithPlaylists(id)
    }

    override fun insert(portfolioEntity: PortfolioEntity): Completable {
        return localDataSource.insert(portfolioEntity)
    }

    override fun insertStock(stockEntity: StockEntity): Completable {
        return Completable.fromCallable{
             localDataSource.insertStock(stockEntity)
        }
    }

    override fun deleteAllStocks(id: Long): Completable {
        return localDataSource.deleteAllStocks(id)
    }

    override fun insertOrAddQuantity(item: StockEntity, payingPrice: Double): Completable {
        return Completable.fromCallable{
            localDataSource.insertOrAddQuantity(item,payingPrice)
        }
    }

    override fun deleteOrSubtractQuantity(item: StockEntity, sellingPrice: Double): Completable {
        return Completable.fromCallable{
            localDataSource.deleteOrSubtractQuantity(item,sellingPrice)
        }
    }

    override fun getStockByUserIdAndSymbol(userId: Long, symbol: String): Observable<StockEntity> {
        return localDataSource.getStockByUserIdAndSymbol(userId,symbol)
    }
}