package rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularStocksData(
    val quotes: List<PopularStocks>
)
