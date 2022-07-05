package rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.R
import rs.raf.jun.andrija_djelic_rn619.databinding.FragmentBuyBinding
import rs.raf.jun.andrija_djelic_rn619.databinding.FragmentQuoteBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PortfolioContract
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.QuoteContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.activities.SplashActivity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PortfolioState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.QuoteState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.StockState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PortfolioViewModel
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.QuoteViewModel
import timber.log.Timber

class QuoteFragment : Fragment(R.layout.fragment_quote){

    private val quoteViewModel: QuoteContract.ViewModel by viewModel<QuoteViewModel>()
    private val portfolioViewModel: PortfolioContract.ViewModel by viewModel<PortfolioViewModel>()


    private var _binding: FragmentQuoteBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)
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

        with(binding.sellBtn){
            isEnabled = false
            isVisible = false
        }
        initListeners()
    }
    private fun initListeners() = with(binding) {

        buyBtn.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_buy_or_sell, BuyFragment())
            fragmentTransaction.commit()



        }
        sellBtn.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_buy_or_sell, SellFragment())
            fragmentTransaction.commit()
            onDestroyView()
        }

    }
    private fun getUserIdFromSharedPreferences(): Long? {
        var sharedPreferences = activity?.getSharedPreferences(
            SplashActivity.PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        return  sharedPreferences?.getLong(SplashActivity.PREF_ID,0)
    }

    private fun initObservers() {
        quoteViewModel.quoteState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        portfolioViewModel.stockState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            showSellButton(it)
        })
        val extras = activity?.intent?.extras
        val quoteSymbol = extras?.getString("STOCK_SYMBOL")
        if (quoteSymbol != null) {
            quoteViewModel.fetchQuoteBySymbol(quoteSymbol)
        }

        getUserIdFromSharedPreferences()?.let {
            if (quoteSymbol != null) {
                portfolioViewModel.getStockByUserIdAndSymbol(it,quoteSymbol)
            }
        }
    }

    private fun showSellButton(state: StockState) {
        when (state) {
            is StockState.Success -> {
                with(binding.sellBtn){
                    isEnabled = true
                    isVisible = true
                }

            }
        }
    }

    private fun renderState(state: QuoteState) {
        when (state) {
            is QuoteState.Success -> {
                with(binding){
                    symbolText.text = state.quote.symbol
                    priceText.text = state.quote.last + state.quote.currency
                    openText.text = state.quote.open
                    bidText.text = state.quote.bid
                    closeText.text = state.quote.close
                    askText.text = state.quote.ask
                    epsText.text = state.quote.metrics.eps
                    ebitText.text = state.quote.metrics.ebit
                    betaText.text = state.quote.metrics.beta


                    val entries: ArrayList<Entry> = ArrayList()
                    var i = 0
                    chartLine.setBackgroundColor(Color.WHITE)
                    chartLine.description.isEnabled = false
                    chartLine.setDrawGridBackground(false)
                    chartLine.setScaleEnabled(true)
                    chartLine.setPinchZoom(true)
                    chartLine.isDragEnabled = true

                    state.quote.chart.bars.forEach {
                        val value = it.price.toFloat()
                        entries.add(Entry(i++.toFloat(), value))
                    }

                    val lineDataSet = LineDataSet(entries, "")

                    lineDataSet.color = Color.BLACK
                    val data = LineData(lineDataSet)
                    chartLine.data = data
                    chartLine.invalidate()
                }
            }
            is QuoteState.Error -> {
                Timber.e("ERROR")
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}