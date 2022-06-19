package com.ranggacikal.challengechapter5.repository

import com.ranggacikal.challengechapter5.model.BattleHistoryResponse
import com.ranggacikal.challengechapter5.model.LoginResponse
import com.ranggacikal.challengechapter5.model.RegisterResponse
import com.ranggacikal.challengechapter5.model.ResponseDataUsers
import com.ranggacikal.challengechapter5.network.ConfigRetrofit
import retrofit2.Call

class RepositoryImpl: Repository {
    override fun getDataUser(): Call<ResponseDataUsers> {
        return ConfigRetrofit.getApiService().getDataUsers()
    }

    override fun getHistoryBattle(): Call<BattleHistoryResponse> {
        return ConfigRetrofit.getApiService().getBattleHistory()
    }

    override fun requestRegister(
        email: String,
        username: String,
        password: String
    ): Call<RegisterResponse> {
        return ConfigRetrofit.getApiService().RegisterUser(email, username, password)
    }

    override fun requestLogin(
        email: String,
        password: String
    ):Call<LoginResponse>{
        return ConfigRetrofit.getApiService().LoginUser(email,password)
    }

}