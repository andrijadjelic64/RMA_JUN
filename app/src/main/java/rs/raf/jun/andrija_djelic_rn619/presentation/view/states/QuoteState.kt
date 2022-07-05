package rs.raf.jun.andrija_djelic_rn619.presentation.view.states

import rs.raf.jun.andrija_djelic_rn619.data.models.quote.Quote
sealed class QuoteState{
    object Loading : QuoteState()
    object DataFetched : QuoteState()
    data class Success(val quote: Quote) : QuoteState()
    data class Error(val message: String) : QuoteState()
}
