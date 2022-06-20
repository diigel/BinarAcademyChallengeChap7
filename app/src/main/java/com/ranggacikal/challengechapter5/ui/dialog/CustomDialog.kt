package com.ranggacikal.challengechapter5.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ranggacikal.challengechapter5.R
import com.ranggacikal.challengechapter5.databinding.CustomDialogBinding

class CustomDialog() : DialogFragment(){
    lateinit var  binding : CustomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CustomDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    fun show(title: String, message: String, isProgress: Boolean){

        dialog?.setContentView(R.layout.custom_dialog)

        binding.tvDialogTitle.text = title.isEmpty().toString()
        binding.tvMessageDialog.text = message.isEmpty().toString()
        if (isProgress){
            binding.progressBar.visibility = View.VISIBLE
        }
        else binding.progressBar.visibility = View.GONE

        dialog?.show()

    }


    fun dismissDialog(){
        dialog?.dismiss()
    }

}