package rs.raf.jun.andrija_djelic_rn619.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PopularStocksState

interface PopularStocksContract {

    interface ViewModel{
        val popularStocksState: LiveData<PopularStocksState>

        fun fetchAllPopularStocks()

    }
}