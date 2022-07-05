package rs.raf.jun.andrija_djelic_rn619.data.models.quote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteMetrics(
    val alpha: String,
    val beta: String,
    val sharp: String,
    val marketCup: String,
    val eps: String,
    val ebit: String
)
