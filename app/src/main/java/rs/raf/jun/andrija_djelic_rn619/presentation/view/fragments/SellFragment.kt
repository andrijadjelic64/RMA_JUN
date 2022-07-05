package rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.R
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.StockEntity
import rs.raf.jun.andrija_djelic_rn619.databinding.FragmentSellBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PortfolioContract
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.QuoteContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.activities.PortfolioActivity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.activities.SplashActivity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.QuoteState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.StockState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PortfolioViewModel
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.QuoteViewModel
import timber.log.Timber

class SellFragment: Fragment(R.layout.fragment_sell) {

    private val quoteViewModel: QuoteContract.ViewModel by viewModel<QuoteViewModel>()
    private val portfolioViewModel: PortfolioContract.ViewModel by viewModel<PortfolioViewModel>()

    private var _binding: FragmentSellBinding? = null

    private val binding get() = _binding!!

    private var quantity: Long = 0
    private var sellingPrice: Double = 0.0
    private var maxQuantity: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellBinding.inflate(inflater, container, false)
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

    private fun initListeners() {
        with(binding) {
            sellAllSharesSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.insertEditText.setText(maxQuantity.toString())
                    insertEditText.setTextIsSelectable(false);
                } else {
                    insertEditText.setText("0")
                    insertEditText.setTextIsSelectable(true);
                }
            }

            insertEditText.doAfterTextChanged {
                if (binding.insertEditText.text.isNotEmpty()){

                    if(insertEditText.text.toString().toInt()>maxQuantity){
                        insertEditText.setText(maxQuantity.toString())
                    }

                    quantity = insertEditText.text.toString().toLong()
                    val stockPrice: Double? = quoteViewModel.quoteState.value?.let { it1 -> getQuotePrice(it1) }
                    sellingPrice = quantity * stockPrice!!
                    resultTextView.text = "You are selling $quantity stocks for $sellingPrice"
                }
            }

            sellBtn.setOnClickListener {

                if(insertEditText.text.toString() != "0"&& quantity>0){
                    quoteViewModel.quoteState.value?.let { it1 -> insertOrUpdateStock(it1) }

                    val text = "Successful transaction"
                    val duration = Toast.LENGTH_LONG

                    val toast = Toast.makeText(activity?.applicationContext , text, duration)
                    toast.show()
                }
            }
        }
    }
    private fun insertOrUpdateStock(state: QuoteState){
        when (state) {
            is QuoteState.Success -> {
                val userId = getUserIdFromSharedPreferences()
                with(state.quote){
                    userId?.let {
                        StockEntity(
                            portfolioId = it,
                            symbol= symbol,
                            quantity = quantity,
                            name = name,
                            currency = currency,
                            last = last.toDouble())
                    }?.let { portfolioViewModel.deleteOrSubtractQuantity(it,sellingPrice) }
                }

                val intent = Intent(this.context, PortfolioActivity::class.java)
                startActivity(intent)

            }
            else->{

            }
        }
    }

    private fun getUserIdFromSharedPreferences(): Long? {
        var sharedPreferences = activity?.getSharedPreferences(
            SplashActivity.PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        return  sharedPreferences?.getLong(SplashActivity.PREF_ID,0)
    }

    private fun getStockQuantity(state: StockState): Long{
        when (state) {
            is StockState.Success -> {
                return state.stock.quantity
            }
            else->{
                return 0
            }
        }
    }

    private fun getQuotePrice(state: QuoteState): Double{
        when (state) {
            is QuoteState.Success -> {
                return state.quote.last.toDouble()
            }
            else->{
                return 0.0
            }
        }
    }



    private fun initObservers() {
        quoteViewModel.quoteState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        portfolioViewModel.stockState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            maxQuantity = getStockQuantity(it)
        })


        val symbol = getSymbol()

        getUserIdFromSharedPreferences()?.let {
            if (symbol != null) {
                portfolioViewModel.getStockByUserIdAndSymbol(
                    it,symbol)
            }
        }

        val quoteSymbol = getSymbol()
        if (quoteSymbol != null) {
            quoteViewModel.fetchQuoteBySymbol(quoteSymbol)
        }

    }

    private fun getSymbol(): String? {
        val extras = activity?.intent?.extras
        return extras?.getString("STOCK_SYMBOL")
    }

    private fun renderState(state: QuoteState) {
        when (state) {
            is QuoteState.Success -> {
                with(binding){
                    stockNameText.text = state.quote.name

                }

            }
            is QuoteState.Error -> {
                Timber.e("Error something occured")
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}