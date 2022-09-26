package com.solanacode.challangech4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.solanacode.challangech4.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding : FragmentSplashBinding
    private lateinit var sharedPref : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        sharedPref = context?.getSharedPreferences("getdataUser", Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var getUsername = sharedPref.getString("username", "false")
        var getPassword = sharedPref.getString("password", "false")

        Handler().postDelayed({
            if (getUsername == "false" && getPassword == "false"){
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
            } else{
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }, 2000)
    }


}