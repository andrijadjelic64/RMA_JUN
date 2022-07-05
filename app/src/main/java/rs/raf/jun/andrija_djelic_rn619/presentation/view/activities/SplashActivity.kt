package rs.raf.jun.andrija_djelic_rn619.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import rs.raf.jun.andrija_djelic_rn619.R
import timber.log.Timber

class SplashActivity: AppCompatActivity() {


    companion object {
        const val PREF_NAME = "userInfo"
        const val PREF_ID = "userID"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            finish()
            init()
        }, 3000) // 3000 is the delayed time in milliseconds.


    }
    private fun init(){

        var sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val loggedIn = sharedPreferences.getLong(PREF_ID,0)
        if(loggedIn == 0L){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }else{
            Timber.e("Pokrecemo MainActivity iz Splash")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}