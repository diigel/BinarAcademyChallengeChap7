package com.ranggacikal.challengechapter5.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import com.ranggacikal.challengechapter5.LoginActivity
import com.ranggacikal.challengechapter5.MainActivity
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.clearValues
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.isLogin
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.isOpenApp
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.token
import com.ranggacikal.challengechapter5.ui.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    lateinit var sharedPreference: PreferenceHelper
    val CUSTOM_PREF_NAME = "user_data"
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreference = PreferenceHelper

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val prefs = sharedPreference.customPreference(this, CUSTOM_PREF_NAME)

        if (!prefs.isLogin && prefs.isOpenApp) {
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }else if (!prefs.isLogin){
            Handler().postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        } else{
            validateToken(prefs)
        }
    }

    private fun validateToken(prefs: SharedPreferences) {
        splashViewModel.getAuth(prefs.token.toString())
        splashViewModel.tokenEvents.observe(this){ response ->
            val status = response?.success
            if (status == false){
                Toast.makeText(this, "Token Expired", Toast.LENGTH_SHORT).show()
                prefs.isLogin = false
                Handler().postDelayed({
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 2000)
            }else{
                Handler().postDelayed({
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 2000)
            }
        }
    }
}