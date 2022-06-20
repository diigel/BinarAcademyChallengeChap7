package com.ranggacikal.challengechapter5.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ranggacikal.challengechapter5.databinding.FragmentMainMenuBinding
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper
import com.ranggacikal.challengechapter5.sharedPreferences.PreferenceHelper.userName

class MainMenuFragment : Fragment() {

    lateinit var binding: FragmentMainMenuBinding
    val CUSTOM_PREF_NAME = "user_data"
    lateinit var sharedPreference: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreference = PreferenceHelper
        val prefs = sharedPreference.customPreference(requireContext(), CUSTOM_PREF_NAME)

        binding.tvPlayerNameMainMenu.text = prefs.userName.toString()
        binding.tvPlayerNameVsComMainMenu.text = prefs.userName.toString()

        val snackBar = Snackbar.make(binding.root, "Selamat Datang ${prefs.userName.toString()}", Snackbar.LENGTH_LONG)
        snackBar.setAction("Tutup") {
            snackBar.dismiss()
        }
        snackBar.show()

        binding.imgVsPlayerMainMenu.setOnClickListener {
            val action = MainMenuFragmentDirections.actionToGame(prefs.userName.toString(), "player")
            Navigation.findNavController(binding.root).navigate(action)
        }

        binding.imgVsComMainMenu.setOnClickListener {
            val action = MainMenuFragmentDirections.actionToGame(prefs.userName.toString(), "com")
            Navigation.findNavController(binding.root).navigate(action)
        }

        binding.imgLeaderBoard.setOnClickListener {
            val actionHistory = MainMenuFragmentDirections.actionToHistory()
            Navigation.findNavController(binding.root).navigate(actionHistory)
        }
    }
}