package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.quote.QuoteService
import rs.raf.jun.andrija_djelic_rn619.data.models.quote.Quote


class QuoteRepositoryImpl(private val remoteDataSource: QuoteService): QuoteRepository {

    override fun fetchQuoteBySymbol(symbol: String): Observable<Quote> {
        return remoteDataSource // posto dovlaci podatke sa interneta onda ide remoteDatasource
            .getQuoteBySymbol(symbol) // sta se desava ispod, mi sada kada dobijemo podatke sa interenta zelimo da upisemo svaki taj podatak u bazu
            .map {

                it.data
            }
    }
}