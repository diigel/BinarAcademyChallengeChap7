package com.ranggacikal.challengechapter5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.ranggacikal.challengechapter5.databinding.ActivityLoginBinding
import com.ranggacikal.challengechapter5.model.LoginResponse
import com.ranggacikal.challengechapter5.network.ConfigRetrofit
import com.ranggacikal.challengechapter5.ui.viewModel.LoginViewModel
import com.ranggacikal.challengechapter5.ui.viewModel.RegisterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            val sharedPreferences = getSharedPreferences("token",Context.MODE_PRIVATE)
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

                ConfigRetrofit.getApiService().LoginUser(email, password)
                    .enqueue(object :Callback<LoginResponse>{
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            var status = response.body()?.success
                            if (status == false) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Username/Password Salah",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
//                                Toast.makeText(
//                                    this@LoginActivity,
//                                    response.body()?.data?.token.toString(),
//                                    Toast.LENGTH_SHORT
//                                ).show()
                                val sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.apply{
                                    putString("token",response.body()?.data?.token)
                                }.apply()
                                val intent = Intent(Intent(this@LoginActivity,MainActivity::class.java))
                                startActivity(intent)
                            }
                        }
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            t.printStackTrace()
                        }



                    })
            }
            btnKembali.setOnClickListener {
                val intt = Intent(Intent(this@LoginActivity,RegisterActivity::class.java))
                startActivity(intt)
            }
        }
    }
}