package rs.raf.jun.andrija_djelic_rn619.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.jun.andrija_djelic_rn619.data.models.user.User
import rs.raf.jun.andrija_djelic_rn619.databinding.ActivityLoginBinding
import rs.raf.jun.andrija_djelic_rn619.presentation.contract.UserContract
import rs.raf.jun.andrija_djelic_rn619.presentation.view.states.UserState
import rs.raf.jun.andrija_djelic_rn619.presentation.viewmodel.UserViewModel
import timber.log.Timber
import java.util.concurrent.locks.ReentrantLock

class LoginActivity : AppCompatActivity()  {

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()




    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }
    private fun initUi(){
        initListeners()

    }
    private fun initListeners() = with(binding) {
        loginBtn.setOnClickListener {

            Timber.e("Login button is clicked")

            val username = username.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()
            Timber.e("Username: $username Email: $email Password: $password")



            userViewModel.getByUsernameAndEmailAndPassword(username,email,password)

            when (userViewModel.userState.value){
                is UserState.Success ->{
                    Timber.e("User has been found")
                }
                is UserState.Error -> {
                    Timber.e("Error in searchin after user")
                }
                is UserState.Loading -> {
                    Timber.e("Loading")
                }
                is UserState.DataFetched -> {
                    Timber.e("Data fetched")
                }
                else -> {
                    Timber.e("Credentials are not valid")
                    val text = "Credentials are not valid"
                    val duration = Toast.LENGTH_LONG

                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                }
            }
        }
    }

    private fun initObservers(){
        userViewModel.userState.observe(this, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        userViewModel.insertUser(User(id = 1, username = "admin",email = "admin@gmail.com", password = "1234"))
    }


    private fun renderState(state: UserState) {
        when (state) {
            is UserState.Success -> {
                Timber.e("User has been found")
                val sharedPreferences = getSharedPreferences(SplashActivity.PREF_NAME, MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                state.loggedUser.id?.let { editor.putLong(SplashActivity.PREF_ID, it) }
                editor.apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            is UserState.Error -> {
                Timber.e("Error in searching after user")
            }

        }
    }

}