package com.ranggacikal.challengechapter5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ranggacikal.challengechapter5.databinding.ActivityMainBinding
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.isOpenApp

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreference: PreferenceHelper
    val CUSTOM_PREF_NAME = "user_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference = PreferenceHelper

        val prefs = sharedPreference.customPreference(this, CUSTOM_PREF_NAME)
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_main)

        if (prefs.isOpenApp){
            graph.startDestination = R.id.landingFragment
            navHostFragment.navController.graph = graph
        }else {
            graph.startDestination = R.id.mainMenuFragment
            navHostFragment.navController.graph = graph
        }

//        navController = Navigation.findNavController(this, R.id.fragmentContainerView)

//        val sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE)
//        val token = sharedPreferences.getString("token","")
//        ConfigRetrofit.getApiService().getUser("Bearer " + token)
//            .enqueue(object :Callback<AuthResponse>{
//                override fun onResponse(
//                    call: Call<AuthResponse>,
//                    response: Response<AuthResponse>
//                ) {
//
//                    if (response.body()?.success == true){
//                        Toast.makeText(this@MainActivity,"Welcome " + response.body()?.data?.username,Toast.LENGTH_SHORT).show()
//                        this@MainActivity.navController = Navigation.findNavController(this@MainActivity, R.id.fragmentContainerView)
//                    } else{
//                        startActivity(Intent(Intent(this@MainActivity,LoginActivity::class.java)))
//                    }
//                }
//
//                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }
//
//            })
    }

    private fun setNavigationGraph() {

    }

    override fun onStart() {
        super.onStart()
//        Navigation.findNavController(binding.root).navigate(R.id.mainMenuFragment)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController, null)
//    }
}