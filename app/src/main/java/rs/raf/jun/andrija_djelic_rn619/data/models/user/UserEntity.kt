package rs.raf.jun.andrija_djelic_rn619.data.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val user_id: Long? = null,
    val username: String,
    val email: String,
    val password: String
    )
