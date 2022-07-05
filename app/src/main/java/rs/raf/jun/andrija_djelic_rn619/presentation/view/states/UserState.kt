package rs.raf.jun.andrija_djelic_rn619.presentation.view.states

import rs.raf.jun.andrija_djelic_rn619.data.models.user.User

sealed class UserState{
    object Loading: UserState()
    object DataFetched: UserState()
    data class Success(val loggedUser: User): UserState()
    data class Error(val message: String): UserState()
}
