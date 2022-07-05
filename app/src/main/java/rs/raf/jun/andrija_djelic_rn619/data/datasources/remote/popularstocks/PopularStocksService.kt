package rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.popularstocks

import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks.PopularStocksResponse

interface PopularStocksService {
    fun getAll(): Observable<PopularStocksResponse>
}