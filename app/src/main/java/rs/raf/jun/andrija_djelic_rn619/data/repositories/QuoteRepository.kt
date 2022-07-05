package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.quote.Quote

interface QuoteRepository {

    fun fetchQuoteBySymbol(symbol:String): Observable<Quote>
}