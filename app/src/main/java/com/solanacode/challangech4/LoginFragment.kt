package com.solanacode.challangech4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.solanacode.challangech4.databinding.FragmentLoginBinding
import kotlin.math.log


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        sharedPref = context?.getSharedPreferences("getdataUser", Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()

        binding.tvRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login(){
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val usernameLog = sharedPref.getString("usernameReg","false")
            val passwordLog = sharedPref.getString("repeatPassword","false")
            if(username.isNotBlank() && password.isNotBlank()){
                if(username.equals(usernameLog) && password.equals(passwordLog)){
                    Navigation.findNavController(binding.root).navigate(R.id.homeFragment)
                }else{
                    Toast.makeText(requireActivity(), "Username atau password anda salah", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireActivity(), "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }


}