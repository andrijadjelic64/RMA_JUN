package rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments

import android.content.Intent
import android.net.Uri
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
import rs.raf.jun.andrija_djelic_rn619.data.models.news.News
import rs.raf.jun.andrija_djelic_rn619.databinding.FragmentNewsBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.NewsContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.recycler.adapter.NewsAdapter
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.NewsState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.NewsViewModel
import timber.log.Timber

class NewsFragment : Fragment(R.layout.fragment_news)  {

    private val newsViewModel: NewsContract.ViewModel by sharedViewModel<NewsViewModel>()

    private var _binding: FragmentNewsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
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
        binding.listRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
//        adapter = BeleskeAdapter(::deleteButton,
//            {id,bool -> beleskeViewModel.updateArchive(id,bool)},
//            {id,bool -> beleskeViewModel.updateArchive(id,bool)},
//            {beleska -> editItem(beleska)})
        adapter = NewsAdapter(::goToWebPage)
        binding.listRv.adapter = adapter
    }

    private fun goToWebPage(news: News){
        val url = news.link
//        val i = Intent(Intent.ACTION_VIEW)
//        i.data = Uri.parse(url)
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        newsViewModel.newsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        newsViewModel.fetchAllNews()
    }

    private fun renderState(state: NewsState) {
        when (state) {
            is NewsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.news)
            }
            is NewsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is NewsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is NewsState.Loading -> {
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