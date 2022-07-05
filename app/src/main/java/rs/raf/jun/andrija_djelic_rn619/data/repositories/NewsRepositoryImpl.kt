package rs.raf.jun.andrija_djelic_rn619.data.repositories

import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.news.NewsService
import rs.raf.jun.andrija_djelic_rn619.data.models.news.News

class NewsRepositoryImpl(private val remoteDataSource: NewsService):NewsRepository  {

    override fun fetchAll(): Observable<List<News>> {
        return remoteDataSource // posto dovlaci podatke sa interneta onda ide remoteDatasource
            .getAll() // sta se desava ispod, mi sada kada dobijemo podatke sa interenta zelimo da upisemo svaki taj podatak u bazu
            .map {

                it.data.newsItems
                /*it.map {
                    News(
                        title = it.title,
                        content= it.content,
                        link= it.link,
                        date= it.date,
                        image= it.image
                    )
                }*/
            }
    }
}