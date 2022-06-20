package com.ranggacikal.challengechapter5.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigRetrofit {

    private  const val BASE_URL = "https://binar-gdd-cc8.herokuapp.com"

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Test-App-Version", "1.0")
                    builder.header("X-Platform", "Android")
                    builder.header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MjVkOTY2NmMzMTU5YjAwMTczZmM5Y2UiLCJ1c2VybmFtZSI6ImFsZGlnYW50ZW5nIiwiZW1haWwiOiJzYWJyaW5hX2JpbmFyQHlvcG1haWwuY29tIiwiaWF0IjoxNjU1NzEwODIzLCJleHAiOjE2NTU3MTgwMjN9.1bCx1QReH7z1J0S4lcakWs1LE7Z6-JDHEbw3eNfXBLg")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()
    }

    private fun provideRetrofit(): Retrofit {

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient()
            .setPrettyPrinting()
            .create()

        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(provideOkHttpClient())

        return builder.build()
    }

    fun getApiService(): ApiService = provideRetrofit().create(ApiService::class.java)

}