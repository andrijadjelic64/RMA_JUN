package rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioEntity
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity
import rs.raf.jun.andrija_djelic_rn619.data.repositories.PortfolioRepository
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PortfolioContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PortfolioState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.StockState
import timber.log.Timber

class PortfolioViewModel(
    private val portfolioRepository: PortfolioRepository
): ViewModel(), PortfolioContract.ViewModel {

    override val portfolioState: MutableLiveData<PortfolioState> = MutableLiveData()
    override val stockState: MutableLiveData<StockState> = MutableLiveData()
    private val subscriptions = CompositeDisposable()

    override fun getPortfolioById(id: Long) {
        val subscription = portfolioRepository
            .getPortfolioById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    portfolioState.value = PortfolioState.Success(it) // ne dovlacimo same podatke vec stanje tih podataka
                },
                {
                    portfolioState.value = PortfolioState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertPortfolio(portfolioEntity: PortfolioEntity) {
        val subscription = portfolioRepository
            .insert(portfolioEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("PORTFOLIO INSERTION COMPLETE")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertStock(stockEntity: StockEntity) {
        val subscription = portfolioRepository
            .insertStock(stockEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("STOCK INSERTION COMPLETE")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteAllStocks(id: Long) {
        val subscription = portfolioRepository
            .deleteAllStocks(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("STOCK INSERTION COMPLETE")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertOrAddQuantity(stockEntity: StockEntity, payingPrice: Double) {
        val subscription = portfolioRepository
            .insertOrAddQuantity(stockEntity, payingPrice)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("STOCK INSERTION/UPDATE COMPLETED")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteOrSubtractQuantity(stockEntity: StockEntity, sellingPrice: Double) {
        val subscription = portfolioRepository
            .deleteOrSubtractQuantity(stockEntity, sellingPrice)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("STOCK INSERTION/UPDATE COMPLETED")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getStockByUserIdAndSymbol(userId: Long, symbol: String) {
        val subscription = portfolioRepository
            .getStockByUserIdAndSymbol(userId, symbol)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    stockState.value = StockState.Success(it)
                    Timber.e("STOCK INSERTION/UPDATE COMPLETED")
                },
                {
                    stockState.value = StockState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)    }
}