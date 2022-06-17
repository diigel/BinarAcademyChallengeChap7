package com.ranggacikal.challengechapter5

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ranggacikal.challengechapter5.databinding.ActivityRegisterBinding
import com.ranggacikal.challengechapter5.ui.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private val registerViewModel : RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            btnRegister.setOnClickListener {
                val email = txtEmail.text.toString().trim()
                val username = txtUsername.text.toString().trim()
                val password = txtPassword.text.toString().trim()

                if (email.isEmpty()){
                    txtEmail.error = "Email is required!"
                    txtEmail.requestFocus()
                    return@setOnClickListener
                }
                if (username.isEmpty()){
                    txtUsername.error = "Username is required!"
                    txtUsername.requestFocus()
                    return@setOnClickListener
                }
                if (password.isEmpty()){
                    txtPassword.error = "Password is required!"
                    txtPassword.requestFocus()
                    return@setOnClickListener
                }

                registerViewModel.requestRegister(email,username, password)
            }

            btnKembali.setOnClickListener {
                val intt = Intent(Intent(this@RegisterActivity,LoginActivity::class.java))
                startActivity(intt)
            }

            registerViewModel.registerEvent.observe(this@RegisterActivity) { registerResponse ->
                if (registerResponse.success == false) {
                    Toast.makeText(this@RegisterActivity, registerResponse.errors, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RegisterActivity, "Berhasil", Toast.LENGTH_SHORT).show()

                    val intent = Intent(Intent(this@RegisterActivity,LoginActivity::class.java))
                    startActivity(intent)
                }
            }
        }
    }

}