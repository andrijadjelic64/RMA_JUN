package rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.andrija_djelic_rn619.data.repositories.QuoteRepository
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.QuoteContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.QuoteState
import timber.log.Timber

class QuoteViewModel(
    private val quoteRepository: QuoteRepository
): ViewModel(), QuoteContract.ViewModel {

    override val quoteState: MutableLiveData<QuoteState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun fetchQuoteBySymbol(symbol: String) {
        val subscription = quoteRepository
            .fetchQuoteBySymbol(symbol)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    quoteState.value = QuoteState.Success(it)
                },
                {
                    quoteState.value = QuoteState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
}