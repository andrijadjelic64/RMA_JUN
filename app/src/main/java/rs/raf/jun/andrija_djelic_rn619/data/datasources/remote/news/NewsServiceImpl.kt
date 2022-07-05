package rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.news

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.news.NewsResponse
import java.io.IOException

class NewsServiceImpl(
    private val context: Context,
    private val moshi: Moshi
) : NewsService {

    override fun getAll(): Observable<NewsResponse> {

        /*val listType = Types.newParameterizedType(List::class.java, NewsResponse::class.java)

        val userJson = getJsonDataFromAsset()
        val jsonAdapter: JsonAdapter<List<NewsResponse>> = moshi.adapter(listType)
        return Observable.just(jsonAdapter.fromJson(userJson)!!)*/

        val userJson = getJsonDataFromAsset()
        val jsonAdapter: JsonAdapter<NewsResponse> = moshi.adapter(NewsResponse::class.java)
        return Observable.just(jsonAdapter.fromJson(userJson)!!)

    }

    private fun getJsonDataFromAsset(): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("getNews.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}
