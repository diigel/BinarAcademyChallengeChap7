package com.ranggacikal.challengechapter5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ranggacikal.challengechapter5.databinding.ActivityLoginBinding
import com.ranggacikal.challengechapter5.ui.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            val sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE)
            val savedKey = sharedPreferences.getString("token", null)
            token.text = savedKey
            btnLogin.setOnClickListener {
                val email = txtEmail.text.toString().trim()
                val password = txtPassword.text.toString().trim()

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
                    Toast.makeText(
                        this@LoginActivity,
                        "Username/Password Salah",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val editor = sharedPreferences.edit()
                    editor.apply {
                        putString("token", response?.data?.token)
                    }.apply()
                    val intent = Intent(Intent(this@LoginActivity, MainActivity::class.java))
                    startActivity(intent)
                }
            }
        }
    }
}