package com.solanacode.challangech4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.solanacode.challangech4.adapter.NotesAdapter
import com.solanacode.challangech4.databinding.FragmentHomeBinding
import com.solanacode.challangech4.model.Notes
import com.solanacode.challangech4.model.NotesViewModel
import com.solanacode.challangech4.room.NotesDatabase
import kotlinx.coroutines.*


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var  viewModel : NotesViewModel
    var dbNotes : NotesDatabase? = null
    val listData = mutableListOf<Notes>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        dbNotes = NotesDatabase.getInstance(requireActivity())
        sharedPref = context?.getSharedPreferences("getdataUser", Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var usernameLog = sharedPref.getString("usernameReg", "false")
        binding.tvPerson.text = "$usernameLog"
        logout()
        createNotes()
        noteVm()
        viewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]
        viewModel.getAllNoteObservers().observe(viewLifecycleOwner){
            notesAdapter.setDataNote(it)
        }
    }

    fun noteVm(){
        notesAdapter = NotesAdapter(mutableListOf())
        binding.rvMain.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvMain.adapter = notesAdapter
    }

    fun getAllNote(){

        GlobalScope.launch {
            var data = dbNotes?.noteDao()?.getDataNote()
            activity?.runOnUiThread{
                notesAdapter = NotesAdapter(data!!)
                binding.rvMain.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvMain.adapter = notesAdapter
            }
        }

    }

    override fun onResume() {
        super.onResume()
        getAllNote()
    }

    private fun logout(){
        binding.tvLogout.setOnClickListener {
            sharedPref.edit().apply {
                clear()
                apply()
            }
            Navigation.findNavController(binding.root).navigate(R.id.loginFragment)
        }
    }

    private fun createNotes(){
        binding.tvaddNotes.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_createFragment)
        }
    }



}