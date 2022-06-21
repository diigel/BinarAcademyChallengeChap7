package com.ranggacikal.challengechapter5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.ranggacikal.challengechapter5.databinding.ActivityLoginBinding
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.isLogin
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.isOpenApp
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.token
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.userName
import com.ranggacikal.challengechapter5.ui.dialog.CustomDialog
import com.ranggacikal.challengechapter5.ui.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    val CUSTOM_PREF_NAME = "user_data"
    lateinit var sharedPreference: PreferenceHelper
//    lateinit var customDialog: CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = PreferenceHelper
//        customDialog = CustomDialog()
        binding.run {
            val sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE)
            val savedKey = sharedPreferences.getString("token", null)
            token.text = savedKey
            btnLogin.setOnClickListener {
                val email = txtEmail.text.toString().trim()
                val password = txtPassword.text.toString().trim()
                binding.pbLogin.visibility = View.VISIBLE
//                customDialog.show("Login", "Wait a moment", true)

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
//                    customDialog.dismissDialog()
                    binding.pbLogin.visibility = View.GONE
                    Toast.makeText(
                        this@LoginActivity,
                        "Username/Password Salah",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
//                    customDialog.dismissDialog()
                    binding.pbLogin.visibility = View.GONE
//                    val editor = sharedPreferences.edit()
                    val prefs = sharedPreference.customPreference(this@LoginActivity, CUSTOM_PREF_NAME)
                    prefs.isLogin = true
                    prefs.token = response?.data?.token.toString()
                    prefs.userName = response?.data?.username.toString()
                    prefs.isOpenApp = false
//                    Navigation.findNavController(binding.root).navigate(R.id.action_to_main_menu)
                    val intent = Intent(Intent(this@LoginActivity, MainActivity::class.java))
                    startActivity(intent)
                }
            }
        }
    }
}