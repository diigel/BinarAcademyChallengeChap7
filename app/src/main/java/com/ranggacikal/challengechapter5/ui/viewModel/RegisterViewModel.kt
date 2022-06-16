package com.ranggacikal.challengechapter5.ui.viewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranggacikal.challengechapter5.model.RegisterResponse
import com.ranggacikal.challengechapter5.network.ConfigRetrofit
import com.ranggacikal.challengechapter5.repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    val repository = RepositoryImpl()

    val registerEvent : MutableLiveData<RegisterResponse> = MutableLiveData()

    fun requestRegister (email : String, username : String, password : String) : LiveData<RegisterResponse> {
        repository.requestRegister(email,username,password)
            .enqueue(object: Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful){
                        registerEvent.value = response.body()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    t.printStackTrace()
                }

            } )

        return registerEvent
    }
}