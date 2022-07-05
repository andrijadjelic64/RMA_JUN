package rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.quote

import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.quote.QuoteResponse

interface QuoteService {

    fun getQuoteBySymbol(symbol:String): Observable<QuoteResponse>
}