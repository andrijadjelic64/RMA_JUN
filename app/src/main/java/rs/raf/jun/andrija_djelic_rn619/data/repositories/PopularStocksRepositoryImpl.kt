package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.popularstocks.PopularStocksService
import rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks.PopularStocks


class PopularStocksRepositoryImpl(private val remoteDataSource: PopularStocksService): PopularStocksRepository {

    override fun fetchAll(): Observable<List<PopularStocks>> {
        return remoteDataSource // posto dovlaci podatke sa interneta onda ide remoteDatasource
            .getAll() // sta se desava ispod, mi sada kada dobijemo podatke sa interenta zelimo da upisemo svaki taj podatak u bazu
            .map {

                it.data.quotes
            }
    }
}