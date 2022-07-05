package rs.raf.jun.andrija_djelic_rn619.data.models.news

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsData(
    val newsItems: List<News>
)
