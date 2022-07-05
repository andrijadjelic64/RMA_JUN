package rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.andrija_djelic_rn619.data.models.user.User
import rs.raf.jun.andrija_djelic_rn619.data.repositories.UserRepository
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.UserContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.InsertUserState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.UserState
import timber.log.Timber

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel(), UserContract.ViewModel{

    override val userState: MutableLiveData<UserState> = MutableLiveData()
    override val insertUserState: MutableLiveData<InsertUserState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()


    override fun getById(id: Long) {
        val subscription = userRepository
            .getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.Success(it) // ne dovlacimo same podatke vec stanje tih podataka
                },
                {
                    userState.value = UserState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getByUsernameAndEmailAndPassword(username: String, email: String, password: String) {
        val subscription = userRepository
            .getByUsernameAndEmailAndPassword(username, email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.Success(it) // ne dovlacimo same podatke vec stanje tih podataka
//                    Timber.e("Succesful getByUsernameAndEmailAndPassword")
                },
                {
                    userState.value = UserState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertUser(user: User) {
        val subscription = userRepository
            .insert(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    insertUserState.value =InsertUserState.Success
                },
                {
                    insertUserState.value = InsertUserState.Error("Error happened while adding note")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
}