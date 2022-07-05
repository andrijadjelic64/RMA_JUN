package rs.raf.jun.andrija_djelic_rn619.presentation.view.activities

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.R
import rs.raf.jun.andrija_djelic_rn619.databinding.ActivityQuoteBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.QuoteContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments.*
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.QuoteState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.QuoteViewModel
import timber.log.Timber

class QuoteActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityQuoteBinding

    private val quoteViewModel: QuoteContract.ViewModel by viewModel<QuoteViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_buy_or_sell, QuoteFragment())
        fragmentTransaction.commit()
        initListeners()
    }

    private fun initListeners() = with(binding) {

//        buyBtn.setOnClickListener {
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.fragment_buy_or_sell, BuyFragment())
//            fragmentTransaction.commit()
//
//        }
//        sellBtn.setOnClickListener {
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.fragment_buy_or_sell, SellFragment())
//            fragmentTransaction.commit()
//        }

    }

    private fun initObservers(){
//        quoteViewModel.quoteState.observe(this, Observer {
//            Timber.e(it.toString())
//            renderState(it)
//        })
//        val extras = intent.extras
//        val quoteSymbol = extras?.getString("STOCK_SYMBOL")
//        if (quoteSymbol != null) {
//            quoteViewModel.fetchQuoteBySymbol(quoteSymbol)
//        }

    }

    private fun renderState(state: QuoteState) {
//        when (state) {
//            is QuoteState.Success -> {
//                with(binding){
//                    symbolText.text = state.quote.symbol
//                    priceText.text = state.quote.last + state.quote.currency
//                    openText.text = state.quote.open
//                    bidText.text = state.quote.bid
//                    closeText.text = state.quote.close
//                    askText.text = state.quote.ask
//                    epsText.text = state.quote.metrics.eps
//                    ebitText.text = state.quote.metrics.ebit
//                    betaText.text = state.quote.metrics.beta
//
//
//                    val entries: ArrayList<Entry> = ArrayList()
//                    var i = 0
//                    chartLine.setBackgroundColor(Color.WHITE)
//                    chartLine.description.isEnabled = false
//                    chartLine.setDrawGridBackground(false)
//                    chartLine.setScaleEnabled(true)
//                    chartLine.setPinchZoom(true)
//                    chartLine.isDragEnabled = true
//
//                    state.quote.chart.bars.forEach {
//                        val value = it.price.toFloat()
//                        entries.add(Entry(i++.toFloat(), value))
//                    }
//
//                    val lineDataSet = LineDataSet(entries, "")
//
//                    lineDataSet.color = Color.BLACK
//                    val data = LineData(lineDataSet)
//                    chartLine.data = data
//                    chartLine.invalidate()
//                }
//            }
//            is QuoteState.Error -> {
//                Timber.e("Error in searching after user")
//            }
//
//        }
    }

}