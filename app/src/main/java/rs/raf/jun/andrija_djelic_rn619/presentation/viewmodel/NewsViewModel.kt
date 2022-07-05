package rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.andrija_djelic_rn619.data.models.Resource
import rs.raf.jun.andrija_djelic_rn619.data.repositories.NewsRepository
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.NewsContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.NewsState
import timber.log.Timber

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel(), NewsContract.ViewModel {

    override val newsState: MutableLiveData<NewsState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()


    override fun fetchAllNews() {
        val subscription = newsRepository
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    newsState.value = NewsState.Success(it)
                },
                {
                    newsState.value = NewsState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


}