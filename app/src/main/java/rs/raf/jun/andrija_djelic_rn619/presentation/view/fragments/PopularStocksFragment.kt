package rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jun.andrija_djelic_rn619.R
import rs.raf.jun.andrija_djelic_rn619.data.models.popularstocks.PopularStocks
import rs.raf.jun.andrija_djelic_rn619.databinding.FragmentPopularStocksBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PopularStocksContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.activities.QuoteActivity
import rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.adapter.PopularStocksAdapter
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.PopularStocksState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PopularStocksViewModel
import timber.log.Timber

class PopularStocksFragment : Fragment(R.layout.fragment_popular_stocks) {

    private val popularStocksViewModel: PopularStocksContract.ViewModel by sharedViewModel<PopularStocksViewModel>()

    private var _binding: FragmentPopularStocksBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: PopularStocksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularStocksBinding.inflate(inflater, container, false)
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
        adapter = PopularStocksAdapter(::openStockTicket)
        binding.listRv.adapter = adapter
    }

    private fun openStockTicket(popularstocks: PopularStocks){
        val intent = Intent(this.context, QuoteActivity::class.java)
        val strName: String = popularstocks.symbol
        intent.putExtra("STOCK_SYMBOL", strName)
        startActivity(intent)
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        popularStocksViewModel.popularStocksState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        popularStocksViewModel.fetchAllPopularStocks()
    }

    private fun renderState(state: PopularStocksState) {
        when (state) {
            is PopularStocksState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.list)
            }
            is PopularStocksState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is PopularStocksState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is PopularStocksState.Loading -> {
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