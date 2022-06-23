package com.ranggacikal.challengechapter5.repository

import com.ranggacikal.challengechapter5.model.*
import retrofit2.Call

interface Repository {
    fun getDataUser(): Call<ResponseDataUsers>
    fun getHistoryBattle(token: String): Call<BattleHistoryResponse>
    fun setBattleResult(token: String, mode: String, result: String): Call<SetBattleResultResponse>
    fun requestRegister(email : String, username : String, password : String) : Call<RegisterResponse>
    fun requestLogin(email : String, password : String) : Call<LoginResponse>
    fun authToken(token: String) : Call<AuthResponse>
}