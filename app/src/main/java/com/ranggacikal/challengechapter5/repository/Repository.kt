package com.ranggacikal.challengechapter5.repository

import com.ranggacikal.challengechapter5.model.ResponseDataUsers
import retrofit2.Call

interface Repository {
    fun getDataUser(): Call<ResponseDataUsers>
}