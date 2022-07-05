package rs.raf.jun.andrija_djelic_rn619.data.models.portfolio

import androidx.room.Embedded
import androidx.room.Relation

data class PortfolioWithStocks(
    @Embedded val porfolio: PortfolioEntity,
    @Relation(
        entity = StockEntity::class,
        parentColumn = "portfolioId",
        entityColumn = "portfolioId"
    )
    val stocks: List<StockEntity>,
    @Relation(
        entity = PortfolioValueHistory::class,
        parentColumn = "portfolioId",
        entityColumn = "portfolioId"
    )
    val valueHistory: List<PortfolioValueHistory>,
)
