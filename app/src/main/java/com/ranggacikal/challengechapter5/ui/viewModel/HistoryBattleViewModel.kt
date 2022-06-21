package com.ranggacikal.challengechapter5.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranggacikal.challengechapter5.model.BattleHistoryResponse
import com.ranggacikal.challengechapter5.repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryBattleViewModel: ViewModel() {

    val historyBattle: MutableLiveData<BattleHistoryResponse> = MutableLiveData()
    val repository = RepositoryImpl()

    fun getHistoryBattleList(authToken: String): LiveData<BattleHistoryResponse> {
        repository.getHistoryBattle("Bearer $authToken").enqueue(object: Callback<BattleHistoryResponse> {
            override fun onResponse(
                call: Call<BattleHistoryResponse>,
                response: Response<BattleHistoryResponse>
            ) {
                if(response.isSuccessful) {
                    historyBattle.postValue(response.body())
                } else {
                    historyBattle.postValue(emptyArray<BattleHistoryResponse>())
                }
            }

            override fun onFailure(call: Call<BattleHistoryResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return historyBattle
    }
}

private fun <T> MutableLiveData<T>.postValue(emptyArray: Array<T>): Array<T> {
return emptyArray
}
