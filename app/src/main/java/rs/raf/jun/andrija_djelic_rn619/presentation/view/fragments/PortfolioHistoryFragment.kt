package rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.R
import rs.raf.jun.andrija_djelic_rn619.databinding.FragmentOwnedStocksBinding
import rs.raf.jun.andrija_djelic_rn619.databinding.FragmentPortfolioHistoryBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PortfolioContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.activities.SplashActivity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PortfolioState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.QuoteState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PortfolioViewModel
import timber.log.Timber

class PortfolioHistoryFragment: Fragment(R.layout.fragment_portfolio_history) {

    private val portfolioViewModel: PortfolioContract.ViewModel by viewModel<PortfolioViewModel>()

    private var _binding: FragmentPortfolioHistoryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPortfolioHistoryBinding.inflate(inflater, container, false)
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
        initListeners()
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
                with(binding){
                    val entries: ArrayList<Entry> = ArrayList()
                    var i = 0
                    chartLine.setBackgroundColor(Color.WHITE)
                    chartLine.description.isEnabled = false
                    chartLine.setDrawGridBackground(false)
                    chartLine.setScaleEnabled(true)
                    chartLine.setPinchZoom(true)
                    chartLine.isDragEnabled = true
                    chartLine.bottom = 0

                    state.portfolio.valueHistory.forEach {
                        val value = it.portfolioValue.toFloat()
                        entries.add(Entry(i++.toFloat(), value))
                    }

                    val lineDataSet = LineDataSet(entries, "")

                    lineDataSet.color = Color.BLACK
                    val data = LineData(lineDataSet)
                    chartLine.data = data
                    chartLine.invalidate()
                }

            }
            is PortfolioState.Error -> {
                Timber.e("ERROR")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}