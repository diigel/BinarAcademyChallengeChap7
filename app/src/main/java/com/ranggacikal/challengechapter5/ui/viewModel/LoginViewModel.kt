package com.ranggacikal.challengechapter5.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranggacikal.challengechapter5.model.LoginResponse
import com.ranggacikal.challengechapter5.model.RegisterResponse
import com.ranggacikal.challengechapter5.repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val repository = RepositoryImpl()

    val loginEvent: MutableLiveData<LoginResponse> = MutableLiveData()

    fun requestLogin(email: String, password: String): LiveData<LoginResponse> {
        repository.requestLogin(email, password)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        loginEvent.value = response.body()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.printStackTrace()
                }

            })

        return loginEvent
    }
}