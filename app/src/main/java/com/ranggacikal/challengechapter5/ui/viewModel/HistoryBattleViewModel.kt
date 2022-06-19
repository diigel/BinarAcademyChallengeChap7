package com.ranggacikal.challengechapter5.ui.viewModel

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

    fun getHistoryBattleList(): LiveData<BattleHistoryResponse> {
        repository.getHistoryBattle().enqueue(object: Callback<BattleHistoryResponse> {
            override fun onResponse(
                call: Call<BattleHistoryResponse>,
                response: Response<BattleHistoryResponse>
            ) {
                if(response.isSuccessful) {
//                    historyBattle.value = response.body()
                    historyBattle.postValue(response.body())
                } else {
                    historyBattle.value = null
//                    historyBattle.postValue(
//
//                    )
                }
            }

            override fun onFailure(call: Call<BattleHistoryResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return historyBattle
    }
}