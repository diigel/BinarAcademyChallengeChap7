package com.ranggacikal.challengechapter5.ui.viewModel

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranggacikal.challengechapter5.model.AuthResponse
import com.ranggacikal.challengechapter5.repository.RepositoryImpl
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel: ViewModel() {

    val repository = RepositoryImpl()
    val tokenEvents: MutableLiveData<AuthResponse> = MutableLiveData()

    fun getAuth(authToken: String): LiveData<AuthResponse>{
        repository.authToken(authToken).enqueue(object : Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) tokenEvents.value = response.body()
                else tokenEvents.value = AuthResponse(success = false)
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return tokenEvents
    }

}