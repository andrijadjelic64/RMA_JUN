package rs.raf.jun.andrija_djelic_rn619.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.jun.andrija_djelic_rn619.data.datasources.local.converters.DateConverter
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioEntity
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioValueHistory
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity
import rs.raf.jun.andrija_djelic_rn619.data.models.user.UserEntity

//ako se ubaci nova tabela
@Database(
    entities = [UserEntity::class, PortfolioEntity::class, StockEntity::class, PortfolioValueHistory::class],
    version = 4,
    exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getPortfolioDao(): PortfolioDao
}