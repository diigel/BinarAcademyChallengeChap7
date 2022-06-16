package com.ranggacikal.challengechapter5.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ConfigRetrofit {

    private  const val BASE_URL = "https://binar-gdd-cc8.herokuapp.com"

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .retryOnConnectionFailure(false)
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)


        return builder.build()
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