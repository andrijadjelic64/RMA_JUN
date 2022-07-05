package rs.raf.jun.andrija_djelic_rn619.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PopularStocksState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.QuoteState

interface QuoteContract {

    interface ViewModel{
        val quoteState: LiveData<QuoteState>

        fun fetchQuoteBySymbol(symbol:String)

    }
}