package rs.raf.jun.andrija_djelic_rn619.data.models.user

import androidx.room.PrimaryKey
import java.io.Serializable

data class User(
    val id: Long?,
    val username: String,
    val email: String,
    val password: String
)