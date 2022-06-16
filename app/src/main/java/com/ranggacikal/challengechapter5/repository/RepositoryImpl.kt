package com.ranggacikal.challengechapter5.repository

import com.ranggacikal.challengechapter5.model.ResponseDataUsers
import com.ranggacikal.challengechapter5.network.ConfigRetrofit
import retrofit2.Call

class RepositoryImpl: Repository {
    override fun getDataUser(): Call<ResponseDataUsers> {
        return ConfigRetrofit.getApiService().getDataUsers()
    }
}