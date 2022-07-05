package rs.raf.jun.andrija_djelic_rn619.data.models.quote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Quote(
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: String,
    val open: String,
    val close: String,
    val bid: String,
    val ask: String,
    val metrics: QuoteMetrics,
    val chart: QuoteChart

)
