package com.ranggacikal.challengechapter5.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ranggacikal.challengechapter5.databinding.ItemHistoryBinding
import com.ranggacikal.challengechapter5.model.BattleHistoryResponse

class HistoryBattleAdapter: RecyclerView.Adapter<HistoryBattleAdapter.HistoryBattleViewHolder>() {

    val historyBattleList: MutableList<BattleHistoryResponse.Data> = mutableListOf()

    fun addHistoryBattleList(list: List<BattleHistoryResponse.Data>){
        historyBattleList.clear()
        historyBattleList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryBattleViewHolder {
        return HistoryBattleViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HistoryBattleViewHolder, position: Int) {
        holder.bind(historyBattleList[position])
    }

    override fun getItemCount(): Int {
        return historyBattleList.size
    }

    inner class HistoryBattleViewHolder(private val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (historyBattleData: BattleHistoryResponse.Data) = binding.run {
            tvItemTime.text = historyBattleData.createdAt
            tvItemHasil.text = historyBattleData.result
            tvItemMode.text = historyBattleData.mode
//            tvItemMessage.text = historyBattleData.message
            tvItemMessage.text = "historyBattleData.message"
        }
    }

}