package rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.andrija_djelic_rn619.data.repositories.PopularStocksRepository
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PopularStocksContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PopularStocksState
import timber.log.Timber

class PopularStocksViewModel(
    private val popularStocksRepository: PopularStocksRepository
): ViewModel(), PopularStocksContract.ViewModel {

    override val popularStocksState: MutableLiveData<PopularStocksState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun fetchAllPopularStocks() {
        val subscription = popularStocksRepository
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    popularStocksState.value = PopularStocksState.Success(it)
                },
                {
                    popularStocksState.value = PopularStocksState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

}