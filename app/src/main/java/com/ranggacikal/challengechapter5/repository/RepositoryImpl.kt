package com.ranggacikal.challengechapter5.repository

import com.ranggacikal.challengechapter5.model.*
import com.ranggacikal.challengechapter5.network.ConfigRetrofit
import retrofit2.Call

class RepositoryImpl: Repository {
    override fun getDataUser(): Call<ResponseDataUsers> {
        return ConfigRetrofit.getApiService().getDataUsers()
    }

    override fun getHistoryBattle(token: String): Call<BattleHistoryResponse> {
        return ConfigRetrofit.getApiService().getBattleHistory(token)
    }

    override fun setBattleResult(token: String, mode: String, result: String): Call<SetBattleResultResponse> {
        return ConfigRetrofit.getApiService().setBattleResult(token, mode, result)
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

    override fun authToken(token: String): Call<AuthResponse> {
        return ConfigRetrofit.getApiService().getUser(token)
    }

}