package com.ranggacikal.challengechapter5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranggacikal.challengechapter5.databinding.FragmentLeaderBoardBinding
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.token
import com.ranggacikal.challengechapter5.ui.adapter.HistoryBattleAdapter
import com.ranggacikal.challengechapter5.ui.viewModel.HistoryBattleViewModel

class LeaderBoardFragment : Fragment() {

    lateinit var binding: FragmentLeaderBoardBinding

    private val viewModel: HistoryBattleViewModel by viewModels()
    private val historybattleadapter by lazy {
        HistoryBattleAdapter()
    }
    lateinit var sharedPreference: PreferenceHelper
    val CUSTOM_PREF_NAME = "user_data"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLeaderBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreference = PreferenceHelper
        val prefs = sharedPreference.customPreference(requireContext(), CUSTOM_PREF_NAME)

        binding.rvHistory.layoutManager = GridLayoutManager(context,1)
        binding.rvHistory.adapter = historybattleadapter

        viewModel.getHistoryBattleList(prefs.token ?: "")
        getHistoryData()
    }

    fun getHistoryData() {
        viewModel.historyBattle.observe(viewLifecycleOwner) { historyList ->
            historyList.let {
                historybattleadapter.addHistoryBattleList(it.data ?: emptyList())
            }
        }
    }
}