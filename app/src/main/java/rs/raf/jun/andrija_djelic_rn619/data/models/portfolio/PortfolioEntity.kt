package rs.raf.jun.andrija_djelic_rn619.data.models.portfolio

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolios")
data class PortfolioEntity(
    @PrimaryKey(autoGenerate = false)
    val portfolioId: Long? = null,
    @ColumnInfo(name = "account_balance")val accountBalance: Double,
    @ColumnInfo(name = "portfolio_Value")val portfolioValue: Double
)
