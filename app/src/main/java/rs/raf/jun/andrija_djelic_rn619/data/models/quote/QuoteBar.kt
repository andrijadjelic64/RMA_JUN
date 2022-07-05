package rs.raf.jun.andrija_djelic_rn619.data.models.quote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteBar(
    val price: String,
    val time: String,
)
