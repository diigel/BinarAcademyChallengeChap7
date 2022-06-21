package com.ranggacikal.challengechapter5.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranggacikal.challengechapter5.model.LoginResponse
import com.ranggacikal.challengechapter5.model.SetBattleResultResponse
import com.ranggacikal.challengechapter5.repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SetBattleResultViewModel: ViewModel() {

    val repository = RepositoryImpl()
    val battleResultEvent: MutableLiveData<SetBattleResultResponse> = MutableLiveData()

    fun setBattleResult(token: String, mode: String, result: String): LiveData<SetBattleResultResponse> {
        repository.setBattleResult("Bearer $token", mode, result).enqueue(object: Callback<SetBattleResultResponse> {
            override fun onResponse(
                call: Call<SetBattleResultResponse>,
                response: Response<SetBattleResultResponse>
            ) {
                if(response.isSuccessful) {
                    battleResultEvent.value = response.body()
                } else {
                    battleResultEvent.value = null
                }
            }

            override fun onFailure(call: Call<SetBattleResultResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return battleResultEvent
    }
}