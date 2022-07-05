package rs.raf.jun.andrija_djelic_rn619.data.models.news

import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News (
    val title: String,
    val content: String,
    val link: String,
    val date: String,
    val image: String
    )