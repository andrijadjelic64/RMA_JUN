package rs.raf.jun.andrija_djelic_rn619.presentation.view.states

sealed class InsertUserState {
    object Success: InsertUserState()
    data class Error(val message: String) : InsertUserState()
}
