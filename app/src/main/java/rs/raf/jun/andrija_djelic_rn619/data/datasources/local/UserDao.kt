package rs.raf.jun.andrija_djelic_rn619.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.user.UserEntity

@Dao
abstract class UserDao {

    @Query("SELECT * FROM users WHERE user_id = :id")
    abstract fun getById(id: Long): Observable<UserEntity>

    @Query("SELECT * FROM users WHERE username = :username AND email = :email AND password = :password")
    abstract fun getByUsernameAndEmailAndPassword(username: String, email: String, password:String): Observable<UserEntity>

    @Insert( onConflict = OnConflictStrategy.ABORT )
    abstract fun insert(entity: UserEntity): Completable
}