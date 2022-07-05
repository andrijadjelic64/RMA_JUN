package rs.raf.jun.andrija_djelic_rn619.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.andrija_djelic_rn619.data.models.user.User
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.InsertUserState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.UserState

interface UserContract {

    interface ViewModel{
        val userState: LiveData<UserState>
        val insertUserState: LiveData<InsertUserState>

        fun getById(id: Long)
        fun getByUsernameAndEmailAndPassword(username:String,email:String, password:String)
        fun insertUser(user: User)

    }
}