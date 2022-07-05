package rs.raf.jun.andrija_djelic_rn619.data.models.portfolio

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PortfolioValueHistory (
    @PrimaryKey(autoGenerate = true)
    val stockBarId: Long? = null,
    val portfolioId: Long,
    val portfolioValue: Double,
    val time: String? = null
)