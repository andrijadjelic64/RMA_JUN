package rs.raf.jun.andrija_djelic_rn619.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.R
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioEntity
import rs.raf.jun.andrija_djelic_rn619.databinding.ActivityPortfolioBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PortfolioContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments.OwnedStocksFragment
import rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments.PortfolioHistoryFragment
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PortfolioState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PortfolioViewModel
import timber.log.Timber

class PortfolioActivity : AppCompatActivity()  {

    private val portfolioViewModel: PortfolioContract.ViewModel by viewModel<PortfolioViewModel>()


    private lateinit var binding: ActivityPortfolioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPortfolioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initObservers()
        initUi()
    }

    private fun initUi() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container_valueHistory, PortfolioHistoryFragment())
        fragmentTransaction.add(R.id.fragment_container_owned_stocks, OwnedStocksFragment())
        fragmentTransaction.commit()
        initListeners()
    }
    private fun initListeners() = with(binding) {

    }

    private fun initObservers(){
        portfolioViewModel.portfolioState.observe(this, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        var sharedPreferences = getSharedPreferences(SplashActivity.PREF_NAME, MODE_PRIVATE)
        val loggedIn = sharedPreferences.getLong(SplashActivity.PREF_ID,0)




        //za inicijalizaciju za slucaj da ne postoji
//        portfolioViewModel.insertPortfolio(PortfolioEntity(loggedIn,10000.0,0.0))
//        portfolioViewModel.insertStock(StockEntity(null,loggedIn,2,"T","General Motors","USD",20.0))
//            portfolioViewModel.deleteAllStocks(loggedIn)
        portfolioViewModel.getPortfolioById(loggedIn)

    }

    private fun renderState(state: PortfolioState) {
        when (state) {
            is PortfolioState.Success -> {
                binding.accBalanceText.text = state.portfolio.porfolio.accountBalance.toString()
                binding.portfolioValueText.text = state.portfolio.porfolio.portfolioValue.toString()
            }
            is PortfolioState.Error -> {
                Timber.e("Error in searching after user")
            }

        }
    }
}