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
import com.solanacode.challangech4.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedPref : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        sharedPref = context?.getSharedPreferences("getdataUser", Context.MODE_PRIVATE)!!
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addUser()



    }
    private fun addUser(){
        binding.btnRegister.setOnClickListener {
            val usernameReg = binding.etusernmaeReg.text.toString().trim()
            val namaReg = binding.etnamaReg.text.toString().trim()
            val password = binding.etpasswordReg.text.toString().trim()
            val repeatPassword = binding.etpasswordRepeat.text.toString().trim()

            if(usernameReg.isNotBlank() && namaReg.isNotBlank() && password.isNotBlank() && repeatPassword.isNotBlank()){
                if(password.equals(repeatPassword)){
                    sharedPref.edit().apply{
                        putString("usernameReg",usernameReg)
                        putString("namaReg",namaReg)
                        putString("password",password)
                        putString("repeatPassword",repeatPassword)
                        apply()
                    }
                    Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
                }else{
                    Toast.makeText(requireActivity(), "Password tidak sama", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(requireActivity(), "Data anda masih kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}