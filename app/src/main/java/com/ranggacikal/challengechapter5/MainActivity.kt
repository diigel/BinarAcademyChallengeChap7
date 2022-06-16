package com.ranggacikal.challengechapter5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.ranggacikal.challengechapter5.model.AuthResponse
import com.ranggacikal.challengechapter5.network.ConfigRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","")
        ConfigRetrofit.getApiService().getUser("Bearer " + token)
            .enqueue(object :Callback<AuthResponse>{
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {

                    if (response.body()?.success == true){
                        Toast.makeText(this@MainActivity,"Welcome " + response.body()?.data?.username,Toast.LENGTH_SHORT).show()
                        this@MainActivity.navController = Navigation.findNavController(this@MainActivity, R.id.fragmentContainerView)
                    } else{
                        startActivity(Intent(Intent(this@MainActivity,LoginActivity::class.java)))
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    t.printStackTrace()
                }

            })
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}