package rs.raf.jun.andrija_djelic_rn619.data.datasources.remote.quote

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import rs.raf.jun.andrija_djelic_rn619.data.models.quote.QuoteResponse
import java.io.IOException

class QuoteServiceImpl(
    private val context: Context,
    private val moshi: Moshi
): QuoteService{

    override fun getQuoteBySymbol(symbol: String): Observable<QuoteResponse> {
        val userJson = getJsonDataFromAsset()
        val jsonAdapter: JsonAdapter<QuoteResponse> = moshi.adapter(QuoteResponse::class.java)
        return Observable.just(jsonAdapter.fromJson(userJson)!!)
    }

    private fun getJsonDataFromAsset(): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("searchQuote_symbol=T.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


}