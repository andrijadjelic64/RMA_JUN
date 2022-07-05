package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.datasources.local.UserDao
import rs.raf.jun.andrija_djelic_rn619.data.models.user.User
import rs.raf.jun.andrija_djelic_rn619.data.models.user.UserEntity
import timber.log.Timber

class UserRepositoryImpl (private val localDataSource: UserDao) :UserRepository {


    init {

    }

    override fun getById(id: Long): Observable<User> {
        return localDataSource.getById(id)
            .map {
                User(
                    it.user_id,
                    it.username,
                    it.email,
                    it.password
                )
            }
    }

    override fun getByUsernameAndEmailAndPassword(username: String, email: String, password: String): Observable<User> {
        return localDataSource.getByUsernameAndEmailAndPassword(username,email, password)
            .map {
                User(
                    it.user_id,
                    it.username,
                    it.email,
                    it.password
                )
            }
    }

    override fun insert(user: User): Completable {
        Timber.e("Insertion user complete")
        return localDataSource.insert(UserEntity(user_id = user.id,username = user.username,email = user.email, password = user.password))
    }
}