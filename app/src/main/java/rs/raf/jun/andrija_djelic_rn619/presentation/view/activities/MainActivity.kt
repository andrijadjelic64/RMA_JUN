package rs.raf.jun.andrija_djelic_rn619.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.data.models.portfolio.PortfolioEntity
import rs.raf.jun.andrija_djelic_rn619.databinding.ActivityMainBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.PortfolioContract
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.UserContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.UserState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.PortfolioViewModel
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.UserViewModel
import timber.log.Timber

class MainActivity: AppCompatActivity()  {

    private val portfolioViewModel: PortfolioContract.ViewModel by viewModel<PortfolioViewModel>()


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    private fun init() {
        initUi()
        test()
    }

    private fun test(){

        var sharedPreferences = getSharedPreferences(SplashActivity.PREF_NAME, MODE_PRIVATE)
        val loggedIn = sharedPreferences.getLong(SplashActivity.PREF_ID,0)




        //za inicijalizaciju za slucaj da ne postoji,
        // u mainu se inicalizuje da pokrije slucaj ako hocemo da kupimo odmah u bez ulaska u portfolio
        portfolioViewModel.insertPortfolio(PortfolioEntity(loggedIn,10000.0,0.0))
    }

    private fun initUi() = with(binding) {
        discover.setOnClickListener {
            startActivity<DiscoverActivity>()
        }
        portfolio.setOnClickListener {
            startActivity<PortfolioActivity>()
        }

        profile.setOnClickListener {
            startActivity<ProfileActivity>()
        }

//        logout.setOnClickListener {
////            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
//            val settings = getSharedPreferences(SplashActivity.PREF_NAME, MODE_PRIVATE)
//            //            settings.edit().remove("loginKey").commit();
//            settings.edit().clear().commit()
//            startActivity<SplashActivity>()
//        }
    }
    private inline fun <reified T> startActivity() {
        startActivity(Intent(this, T::class.java))
    }
}