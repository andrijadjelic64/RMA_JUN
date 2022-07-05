package rs.raf.jun.andrija_djelic_rn619.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.databinding.ActivityProfileBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.UserContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.UserState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.UserViewModel
import timber.log.Timber

class ProfileActivity : AppCompatActivity(){

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initUi()
        initObservers()

    }

    private fun initUi(){

        var sharedPreferences = getSharedPreferences(SplashActivity.PREF_NAME, MODE_PRIVATE)
        val loggedIn = sharedPreferences.getLong(SplashActivity.PREF_ID,0)
        Timber.e(loggedIn.toString())
        userViewModel.getById(loggedIn)

        initListeners()
    }

    private fun initListeners() = with(binding) {

//

        logoutBtn.setOnClickListener {
//            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
            val settings = getSharedPreferences(SplashActivity.PREF_NAME, MODE_PRIVATE)
            //            settings.edit().remove("loginKey").commit();
            settings.edit().clear().commit()
            startActivity<SplashActivity>()
        }
    }
    private fun initObservers(){
        userViewModel.userState.observe(this, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

//        userViewModel.insertUser(User(id = null, username = "admin",email = "admin@gmail.com", password = "1234"))
    }

    private fun renderState(state: UserState) {
        when (state) {
            is UserState.Success -> {
                binding.usernameTextView.text = state.loggedUser.username
                binding.emailTextView.text = state.loggedUser.email
            }
            is UserState.Error -> {
                Timber.e("Error in searching after user")
            }

        }
    }

    private inline fun <reified T> startActivity() {
        startActivity(Intent(this, T::class.java))
    }
}