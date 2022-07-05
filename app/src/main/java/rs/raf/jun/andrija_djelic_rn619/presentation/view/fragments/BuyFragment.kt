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
import rs.raf.jun.andrija_djelic_rn619.databinding.FragmentBuyBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PortfolioContract
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.QuoteContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.activities.PortfolioActivity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.activities.SplashActivity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PortfolioState
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.QuoteState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PortfolioViewModel
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.QuoteViewModel
import timber.log.Timber

class BuyFragment : Fragment(R.layout.fragment_buy){
    private val quoteViewModel: QuoteContract.ViewModel by viewModel<QuoteViewModel>()
    private val portfolioViewModel: PortfolioContract.ViewModel by viewModel<PortfolioViewModel>()



    private var _binding: FragmentBuyBinding? = null

    private val binding get() = _binding!!

    private var quantity: Long = 0
    private var payingPrice: Double = 0.0
    private var stockPrice: Double =0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        getUserIdFromSharedPreferences()?.let { portfolioViewModel.getPortfolioById(it) }

        initUi()
        initObservers()
    }

    private fun initUi() {
        initListeners()
    }

    private fun initListeners() {
        with(binding){
            buySharesSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    insertEditText.hint = "Insert amount of stocks"
                    insertEditText.setText("")
                } else {
                    insertEditText.hint = "Insert amount of money"
                    insertEditText.setText("")
                }
            }



            insertEditText.doAfterTextChanged {
                if (binding.insertEditText.text.isNotEmpty()){

                    if(binding.buySharesSwitch.isChecked){

//                        val stockPrice = quoteViewModel.quoteState.value?.let { it1 -> getQuotePrice(it1) }
                        quantity = insertEditText.text.toString().toLong()
                        payingPrice = quantity * stockPrice!!
                        resultTextView.text = "You are paying $payingPrice"
                    }else{
//                        val stockPrice: Double? = quoteViewModel.quoteState.value?.let { it1 -> getQuotePrice(it1) }
                        val bid = insertEditText.text.toString().toDouble()
                        quantity = (bid/ stockPrice!!).toLong()
                        payingPrice = quantity * stockPrice
                        resultTextView.text = "You will pay $payingPrice for $quantity stocks"
                    }
                }

            }

            buyBtn.setOnClickListener {

                if(insertEditText.text.toString() != "0"&& quantity>0){
                    val flag = portfolioViewModel.portfolioState.value?.let { it1 -> checkBalance(it1) }

                    if(flag == true){
                        quoteViewModel.quoteState.value?.let { it1 -> insertOrUpdateStock(it1) }
                        val text = "Successful transaction"
                        val duration = Toast.LENGTH_LONG

                        val toast = Toast.makeText(activity?.applicationContext , text, duration)
                        toast.show()


                    }else{
                        val text = "Your account balance is too low"
                        val duration = Toast.LENGTH_LONG

                        val toast = Toast.makeText(activity?.applicationContext , text, duration)
                        toast.show()
                    }
                }

            }
        }
    }

    private fun checkBalance(state: PortfolioState): Boolean{
        when (state) {
            is PortfolioState.Success -> {
                if(state.portfolio.porfolio.accountBalance >= payingPrice)
                    return true
                else false
            }
        }
        return false
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
                    }?.let { portfolioViewModel.insertOrAddQuantity(it,payingPrice) }
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





    private fun getQuotePrice(state: QuoteState){
        when (state) {
            is QuoteState.Success -> {
                stockPrice= state.quote.last.toDouble()
            }
            else->{

            }
        }
    }

    private fun initObservers() {
        quoteViewModel.quoteState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
            getQuotePrice(it)
        })


        val extras = activity?.intent?.extras
        val quoteSymbol = extras?.getString("STOCK_SYMBOL")
        if (quoteSymbol != null) {
            quoteViewModel.fetchQuoteBySymbol(quoteSymbol)
        }



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