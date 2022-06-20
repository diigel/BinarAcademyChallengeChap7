package com.ranggacikal.challengechapter5.network

import com.ranggacikal.challengechapter5.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("getDataUser")
    fun getDataUsers(): Call<ResponseDataUsers>
    @GET("api/v1/battle")
    fun getBattleHistory(
        @Header("Authorization") authHeader: String? = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MjVkOTY2NmMzMTU5YjAwMTczZmM5Y2UiLCJ1c2VybmFtZSI6ImFsZGlnYW50ZW5nIiwiZW1haWwiOiJzYWJyaW5hX2JpbmFyQHlvcG1haWwuY29tIiwiaWF0IjoxNjU1NzEwODIzLCJleHAiOjE2NTU3MTgwMjN9.1bCx1QReH7z1J0S4lcakWs1LE7Z6-JDHEbw3eNfXBLg"
    ): Call<BattleHistoryResponse>

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