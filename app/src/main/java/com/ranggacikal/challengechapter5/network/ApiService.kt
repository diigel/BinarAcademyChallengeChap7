package com.ranggacikal.challengechapter5.network

import com.ranggacikal.challengechapter5.model.AuthResponse
import com.ranggacikal.challengechapter5.model.LoginResponse
import com.ranggacikal.challengechapter5.model.RegisterResponse
import com.ranggacikal.challengechapter5.model.ResponseDataUsers
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("getDataUser")
    fun getDataUsers(): Call<ResponseDataUsers>

    @GET("api/v1/auth/me")
    fun getUser(
        @Header("Authorization") authHeader: String?
    ): Call<AuthResponse>

    @FormUrlEncoded
    @POST("api/v1/auth/register")
    fun RegisterUser(
        @Field("email") email:String,
        @Field("username") username:String,
        @Field("password")password:String
    ):Call<RegisterResponse>

    @FormUrlEncoded
    @POST("api/v1/auth/login")
    fun LoginUser(
        @Field("email") email:String,
        @Field("password")password:String
    ):Call<LoginResponse>
}