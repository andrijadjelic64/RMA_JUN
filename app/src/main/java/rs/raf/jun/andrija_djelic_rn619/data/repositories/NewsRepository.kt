package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.news.News

interface NewsRepository {

    fun fetchAll(): Observable<List<News>> // dovlacenje podataka sa interneta
}