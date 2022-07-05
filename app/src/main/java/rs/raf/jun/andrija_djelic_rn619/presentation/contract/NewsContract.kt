package rs.raf.jun.andrija_djelic_rn619.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.NewsState

interface NewsContract {

    interface ViewModel{
        val newsState: LiveData<NewsState>

        fun fetchAllNews()

    }
}