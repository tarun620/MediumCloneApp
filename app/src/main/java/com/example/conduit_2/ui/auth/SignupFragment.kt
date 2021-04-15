package com.example.conduit_2.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.conduit_2.AuthViewModel
import com.example.conduit_2.databinding.FragmentLoginSignupBinding

class SignupFragment : Fragment() {

    private var _binding: FragmentLoginSignupBinding?=null

    //    lateinit var authViewModel:AuthViewModel
    val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentLoginSignupBinding.inflate(inflater,container,false)
//        authViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)
        _binding?.usernameEditText?.isVisible=true
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            submitButton.setOnClickListener {
                authViewModel.signup(usernameEditText.text.toString(), emailEditText.text.toString(), passwordEditText.text.toString())
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}