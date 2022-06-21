package com.ranggacikal.challengechapter5

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ranggacikal.challengechapter5.databinding.ActivityLoginBinding
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.isLogin
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.isOpenApp
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.token
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.userName
import com.ranggacikal.challengechapter5.ui.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    val CUSTOM_PREF_NAME = "user_data"
    lateinit var sharedPreference: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = PreferenceHelper
        binding.run {
            btnLogin.setOnClickListener {
                val email = txtEmail.text.toString().trim()
                val password = txtPassword.text.toString().trim()
                binding.pbLogin.visibility = View.VISIBLE

                if (email.isEmpty()) {
                    txtEmail.error = "Email is required!"
                    txtEmail.requestFocus()
                    return@setOnClickListener
                }
                if (password.isEmpty()) {
                    txtPassword.error = "Password is required!"
                    txtPassword.requestFocus()
                    return@setOnClickListener
                }
                loginViewModel.requestLogin(email, password)
            }
            btnKembali.setOnClickListener {
                val intt = Intent(Intent(this@LoginActivity, RegisterActivity::class.java))
                startActivity(intt)
            }

            loginViewModel.loginEvent.observe(this@LoginActivity) { response ->
                val status = response?.success
                if (status == false) {
                    binding.pbLogin.visibility = View.GONE
                    Toast.makeText(
                        this@LoginActivity,
                        "Username/Password Salah",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.pbLogin.visibility = View.GONE
                    val prefs = sharedPreference.customPreference(this@LoginActivity, CUSTOM_PREF_NAME)
                    prefs.isLogin = true
                    prefs.token = response?.data?.token.toString()
                    prefs.userName = response?.data?.username.toString()
                    prefs.isOpenApp = false
                    val intent = Intent(Intent(this@LoginActivity, MainActivity::class.java))
                    startActivity(intent)
                }
            }
        }
    }
}