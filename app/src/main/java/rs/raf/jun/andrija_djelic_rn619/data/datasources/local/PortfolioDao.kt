package rs.raf.jun.andrija_djelic_rn619.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioEntity
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioWithStocks
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioValueHistory
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity

@Dao
abstract class PortfolioDao {


    @Insert( onConflict = OnConflictStrategy.ABORT )
    abstract fun insert(entity: PortfolioEntity): Completable

    @Transaction
    @Query("SELECT * FROM portfolios WHERE portfolioId = :userId")
    abstract fun getUsersWithPlaylists(userId:Long): Observable<PortfolioWithStocks>


    @Query("SELECT * FROM portfolios WHERE portfolioId = :userId")
    abstract fun getPortfolioById(userId: Long): List<PortfolioEntity>

    @Query("SELECT * FROM owned_stocks WHERE portfolioId = :userId AND symbol = :symbol")
    abstract fun getStockByUserIdAndSymbol(userId: Long, symbol: String): Observable<StockEntity>

    @Query("DELETE FROM owned_stocks WHERE portfolioId = :userId ")
    abstract fun deleteAllStocks(userId:Long): Completable

    @Query("DELETE FROM owned_stocks WHERE portfolioId = :userId AND symbol = :symbol")
    abstract fun deleteStockByIdAndSymbol(userId: Long, symbol: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertStock(entity: StockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPortfolioValueHistory(portfolioValueHistory: PortfolioValueHistory)

    @Query("UPDATE owned_stocks SET quantity = quantity + :quantity WHERE portfolioId = :userId AND symbol = :symbol")
    abstract fun addQuantity(userId:Long, symbol:String, quantity:Long)

    @Query("UPDATE owned_stocks SET quantity = quantity - :quantity WHERE portfolioId = :userId AND symbol = :symbol")
    abstract fun subtractQuantity(userId:Long, symbol:String, quantity:Long)

    @Query("UPDATE portfolios SET portfolio_Value = :portfolioValue WHERE portfolioId = :userId")
    abstract fun updatePortfolioValueByUserId(userId:Long, portfolioValue:Double)

    @Query("SELECT * FROM owned_stocks WHERE portfolioId = :userId AND symbol = :symbol")
    abstract fun getStockWithUserIdAndSymbol(userId:Long, symbol:String): List<StockEntity>

    @Query("SELECT * FROM owned_stocks WHERE portfolioId = :userId")
    abstract fun getAllStocksByUserId(userId:Long): List<StockEntity>

    @Query("UPDATE portfolios SET account_balance = account_balance - :payingPrice WHERE portfolioId = :userId")
    abstract fun lowerBalanceAccount(userId:Long, payingPrice:Double)

    @Query("UPDATE portfolios SET account_balance = account_balance + :payingPrice WHERE portfolioId = :userId")
    abstract fun liftBalanceAccount(userId:Long, payingPrice:Double)

    @Query("SELECT * FROM owned_stocks WHERE portfolioId = :userId AND symbol = :symbol")
    abstract fun getStockByUserIdAndSymbolTransaction(userId: Long, symbol: String): StockEntity

    @Transaction
    open fun insertOrAddQuantity(item: StockEntity, payingPrice: Double) {
            val itemsFromDB : List<StockEntity> = getStockWithUserIdAndSymbol(item.portfolioId,item.symbol)
            if (itemsFromDB.isEmpty()) {
                insertStock(item)
                lowerBalanceAccount(item.portfolioId, payingPrice)
                updatePortfolioValue(item.portfolioId)
            }
            else{
                addQuantity(item.portfolioId, item.symbol,item.quantity)
                lowerBalanceAccount(item.portfolioId,payingPrice)
                updatePortfolioValue(item.portfolioId)
            }
    }

    @Transaction
    open fun updatePortfolioValue(userId: Long){
        val itemsFromDB : List<StockEntity> = getAllStocksByUserId(userId)

        var portfolioValue: Double = 0.0

        itemsFromDB.forEach {
            portfolioValue += it.last*it.quantity
        }

        updatePortfolioValueByUserId(userId,portfolioValue)

        insertPortfolioValueHistory(PortfolioValueHistory(portfolioId = userId,portfolioValue = portfolioValue))

    }




    @Transaction
    open fun deleteOrSubtractQuantity(item: StockEntity, payingPrice: Double) {

        subtractQuantity(item.portfolioId, item.symbol,item.quantity)
        liftBalanceAccount(item.portfolioId,payingPrice)
        updatePortfolioValue(item.portfolioId)
        val stockItem = getStockByUserIdAndSymbolTransaction(item.portfolioId, item.symbol)
        if(stockItem.quantity ==0L){
            deleteStockByIdAndSymbol(item.portfolioId, item.symbol)
        }

    }

}