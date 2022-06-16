package com.ranggacikal.challengechapter5.repository

import com.ranggacikal.challengechapter5.model.LoginResponse
import com.ranggacikal.challengechapter5.model.RegisterResponse
import com.ranggacikal.challengechapter5.model.ResponseDataUsers
import retrofit2.Call

interface Repository {
    fun getDataUser(): Call<ResponseDataUsers>

    fun requestRegister(email : String, username : String, password : String) : Call<RegisterResponse>

    fun requestLogin(email : String, password : String) : Call<LoginResponse>
}