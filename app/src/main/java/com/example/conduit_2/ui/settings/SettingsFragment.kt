package com.example.conduit_2.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.conduit_2.AuthViewModel
import com.example.conduit_2.databinding.FragmentSettingsBinding

class SettingsFragment :Fragment(){

    private val authViewModel by activityViewModels<AuthViewModel>()
    private var _binding:FragmentSettingsBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSettingsBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user?.observe({lifecycle}){
            _binding?.apply {
                bioEditText.setText(it?.bio)
                emailEditText.setText(it?.email)
                usernameEditText.setText(it?.username)
//                imageEditText.setText(it?.image)
            }
        }
        _binding?.apply {
            submitButton?.setOnClickListener {
                authViewModel.update(
                    bioEditText.text.toString(),
                    usernameEditText.text.toString().takeIf { it.isNotBlank() },
                    imageEditText.text.toString(),
                    emailEditText.text.toString().takeIf { it.isNotBlank() },
                    passwordEditText.text.toString().takeIf { it.isNotBlank() }
                )
            }
        }
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
    }
}