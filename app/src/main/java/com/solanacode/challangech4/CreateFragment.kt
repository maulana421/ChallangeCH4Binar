package com.solanacode.challangech4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.solanacode.challangech4.databinding.FragmentCreateBinding
import com.solanacode.challangech4.model.Notes
import com.solanacode.challangech4.room.NotesDatabase
import kotlinx.coroutines.*


class CreateFragment : Fragment() {

    private lateinit var binding : FragmentCreateBinding
    private val dbNotes by lazy {
        NotesDatabase(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateBinding.inflate(layoutInflater)

        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnsaveNotes.setOnClickListener {
          CoroutineScope(Dispatchers.IO).launch {
              var judul = binding.etcreateJudul.text.toString()
              var desc = binding.etcreateDesc.text.toString()

                dbNotes.noteDao().insertNote(Notes(0,judul,desc))
//                dbNote!!.noteDao().insertNote(Notes(0,judul, desc))
//            Toast.makeText(context, "tambah sukses", Toast.LENGTH_SHORT).show()
              Log.d("DATA","${dbNotes.noteDao().getDataNote()}")

          }

            Navigation.findNavController(binding.root).navigate(R.id.homeFragment)




        }
    }




}