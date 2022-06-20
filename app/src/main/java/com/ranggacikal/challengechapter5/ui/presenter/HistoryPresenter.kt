package com.ranggacikal.challengechapter5.ui.presenter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ranggacikal.challengechapter5.db.HistoryDatabase
import com.ranggacikal.challengechapter5.model.BattleHistoryResponse
import com.ranggacikal.challengechapter5.repository.RepositoryImpl
import com.ranggacikal.challengechapter5.ui.LeaderBoardFragment
import com.ranggacikal.challengechapter5.ui.model.HistoryData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryPresenter(val data: HistoryView) {

//    private var db: HistoryDatabase? = null
//
//    fun getListHistory(context: Context){
//        db = HistoryDatabase.getInstance(context)
//        GlobalScope.launch {
//            val historyList = db?.historyDao()?.getAllHistory()
//            historyList.let {
//                val historyData = HistoryData()
//                historyData.historyData = it
//                data.historyList(historyData)
//            }
//        }
//    }

    val historyBattle: MutableLiveData<BattleHistoryResponse> = MutableLiveData()
    val repository = RepositoryImpl()

    fun getHistoryBattleList(): LiveData<BattleHistoryResponse> {
        repository.getHistoryBattle().enqueue(object: Callback<BattleHistoryResponse> {
            override fun onResponse(
                call: Call<BattleHistoryResponse>,
                response: Response<BattleHistoryResponse>
            ) {
                Log.i("TAG", "onResponse: ${response.body()}")
                if(response.isSuccessful) {
                    historyBattle.postValue(response.body())
                } else {
                    historyBattle.value = null
                }
            }

            override fun onFailure(call: Call<BattleHistoryResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return historyBattle
    }

}