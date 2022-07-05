package rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.popularstocks

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks.PopularStocksResponse
import java.io.IOException

class PopularStocksServiceImpl(
    private val context: Context,
    private val moshi: Moshi
): PopularStocksService {

    override fun getAll(): Observable<PopularStocksResponse> {
        val userJson = getJsonDataFromAsset()
        val jsonAdapter: JsonAdapter<PopularStocksResponse> = moshi.adapter(PopularStocksResponse::class.java)
        return Observable.just(jsonAdapter.fromJson(userJson)!!)
    }

    private fun getJsonDataFromAsset(): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("getIndexes.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}