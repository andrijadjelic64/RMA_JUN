package rs.raf.jun.andrija_djelic_rn619.data.models.portfolio

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "owned_stocks")
data class StockEntity(
    @PrimaryKey(autoGenerate = true)
    val stockId: Long? = null,
    val portfolioId: Long,
    val symbol: String,
    val quantity: Long,
    val name: String,
    val currency: String,
    val last: Double,
)
