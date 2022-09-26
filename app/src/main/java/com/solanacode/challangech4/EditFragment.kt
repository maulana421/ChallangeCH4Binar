package com.solanacode.challangech4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.solanacode.challangech4.databinding.FragmentEditBinding
import com.solanacode.challangech4.model.Notes
import com.solanacode.challangech4.model.NotesViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class EditFragment : Fragment() {


    private val note by navArgs<EditFragmentArgs>()
    private lateinit var binding : FragmentEditBinding
    lateinit var viewModel : NotesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eteditJudul.setText(note.noteedit.judul)
        binding.eteditDesc.setText(note.noteedit.desc)
        editNotes()
    }

    fun editNotes(){
       binding.btneditNotes.setOnClickListener {
           GlobalScope.async {
               var title = binding.eteditJudul.text.toString()
               var desc = binding.eteditDesc.text.toString()

               val editNote = Notes(note.noteedit.id, title, desc)
               viewModel.updateNote(editNote)

               Navigation.findNavController(binding.root).navigate(R.id.homeFragment)
           }
       }
    }




}