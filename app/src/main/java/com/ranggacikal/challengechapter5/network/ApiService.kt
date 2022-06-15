package com.ranggacikal.challengechapter5.network

import com.ranggacikal.challengechapter5.model.ResponseDataUsers
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("getDataUser")
    fun getDataUsers(): Call<ResponseDataUsers>
}