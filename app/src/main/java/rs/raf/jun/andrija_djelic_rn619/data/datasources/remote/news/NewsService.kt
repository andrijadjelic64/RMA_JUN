package rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.news

import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.news.NewsResponse

interface NewsService {
    fun getAll(): Observable<NewsResponse>
}