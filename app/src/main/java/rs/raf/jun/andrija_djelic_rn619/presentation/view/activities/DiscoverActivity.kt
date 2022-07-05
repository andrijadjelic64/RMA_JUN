package rs.raf.jun.andrija_djelic_rn619.presentation.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.R
import rs.raf.jun.andrija_djelic_rn619.databinding.ActivityDiscoverBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.UserContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments.NewsFragment
import rs.raf.jun.andrija_djelic_rn619.presentation.view.fragments.PopularStocksFragment
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.UserViewModel

class DiscoverActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityDiscoverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscoverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container_news, NewsFragment())
        fragmentTransaction.add(R.id.fragment_container_popular_stocks, PopularStocksFragment())
        fragmentTransaction.commit()
    }
}