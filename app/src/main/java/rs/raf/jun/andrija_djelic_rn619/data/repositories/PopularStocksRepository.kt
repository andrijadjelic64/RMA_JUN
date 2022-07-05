package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks.PopularStocks

interface PopularStocksRepository {
    fun fetchAll(): Observable<List<PopularStocks>> // dovlacenje podataka sa interneta

}