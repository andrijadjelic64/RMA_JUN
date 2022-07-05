package rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularStocks(
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: String,
    val changeFromPreviousClose: String,
    val percentChangeFromPreviousClose: String,
    val marketName: String,
    val chart: PopularStocksChart
)
