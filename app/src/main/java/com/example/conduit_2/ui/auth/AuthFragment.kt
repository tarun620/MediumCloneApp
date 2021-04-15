package com.example.conduit_2.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import com.example.conduit_2.R
import com.example.conduit_2.databinding.FragmentAuthBinding
import com.google.android.material.tabs.TabLayout

class AuthFragment : Fragment() {

    private var _binding:FragmentAuthBinding?=null
    private var navController:NavController?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAuthBinding.inflate(inflater,container,false)
//        navController=activity?.let { Navigation.findNavController(it,R.id.authFragmentNavHost) }
//        navController=_binding?.let { Navigation.findNavController(it.root) }
//        Navigation.findNavController(_binding!!.root)


        return _binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController=_binding?.let { Navigation.findNavController(it.root.findViewById(R.id.authFragmentNavHost)) }

        _binding?.authTabLayout?.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        navController?.navigate(R.id.gotoLoginFragment)
                    }
                    1->{
                        navController?.navigate(R.id.gotoSignupFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}