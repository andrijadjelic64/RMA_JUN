package rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.R
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity
import rs.raf.jun.andrija_djelic_rn619.databinding.FragmentOwnedStocksBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PopularStocksContract
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PortfolioContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.activities.QuoteActivity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.activities.SplashActivity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.adapter.PortfolioAdapter
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PortfolioState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PopularStocksViewModel
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PortfolioViewModel
import timber.log.Timber

class OwnedStocksFragment: Fragment(R.layout.fragment_owned_stocks) {

    private val portfolioViewModel: PortfolioContract.ViewModel by viewModel<PortfolioViewModel>()
    private val popularStocksViewModel: PopularStocksContract.ViewModel by sharedViewModel<PopularStocksViewModel>()



    private var _binding: FragmentOwnedStocksBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: PortfolioAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOwnedStocksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }
    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context)
//        adapter = BeleskeAdapter(::deleteButton,
//            {id,bool -> beleskeViewModel.updateArchive(id,bool)},
//            {id,bool -> beleskeViewModel.updateArchive(id,bool)},
//            {beleska -> editItem(beleska)})
        adapter = PortfolioAdapter(::openStockTicket)
        binding.listRv.adapter = adapter
    }

    private fun openStockTicket(stockEntity: StockEntity){
        val intent = Intent(this.context, QuoteActivity::class.java)
        val strName: String = stockEntity.symbol
        intent.putExtra("STOCK_SYMBOL", strName)
        startActivity(intent)
    }


    private fun initListeners() = with(binding) {
    }

    private fun initObservers() {
        portfolioViewModel.portfolioState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        var sharedPreferences = activity?.getSharedPreferences(
            SplashActivity.PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        val loggedIn = sharedPreferences?.getLong(SplashActivity.PREF_ID,0)
        if (loggedIn != null) {
            portfolioViewModel.getPortfolioById(loggedIn)
        }


    }

    private fun renderState(state: PortfolioState) {
        when (state) {
            is PortfolioState.Success -> {
                Timber.e("RenderState Succes fired")
                showLoadingState(false)
                adapter.submitList(state.portfolio.stocks)
            }
            is PortfolioState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is PortfolioState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is PortfolioState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.listRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}