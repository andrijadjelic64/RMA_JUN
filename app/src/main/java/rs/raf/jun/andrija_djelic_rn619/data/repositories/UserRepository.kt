package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.user.User

interface UserRepository {

    fun getById(id: Long): Observable<User>
    fun getByUsernameAndEmailAndPassword(username:String,email:String, password:String): Observable<User>
    fun insert(user: User): Completable
}