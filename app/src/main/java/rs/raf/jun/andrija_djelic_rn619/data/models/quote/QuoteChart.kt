package rs.raf.jun.andrija_djelic_rn619.data.models.quote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteChart(
    val bars: List<QuoteBar>
)
