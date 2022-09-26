package com.solanacode.challangech4.adapter

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.solanacode.challangech4.*
import com.solanacode.challangech4.databinding.FragmentHomeBinding
import com.solanacode.challangech4.databinding.ItemNotesBinding
import com.solanacode.challangech4.model.Notes
import com.solanacode.challangech4.room.NotesDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NotesAdapter(var listNote : List<Notes>): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var dbNotes : NotesDatabase?= null
    class NotesViewHolder(var binding : ItemNotesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        var view = ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
       holder.binding.notes = listNote[position]
       holder.binding.ivDeleteNotes.setOnClickListener {
           dbNotes = NotesDatabase.getInstance(it.context)
               val alert = AlertDialog.Builder(it.context)
               alert.apply {
                   setTitle("Warning")
                   setMessage("Apakah anda akan menghapus Catatan ini?")
                   setPositiveButton("Hapus"){ dialogInterface: DialogInterface, i: Int ->
                       GlobalScope.async {
                           val delete = dbNotes?.noteDao()?.deleteNote(listNote[position])
                           val nav = Navigation.findNavController(it)
                           nav.run {
                               navigate(R.id.homeFragment)
                           }
                       }
                   }
                   setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int -> }
               }
               alert.show()

       }
        holder.binding.ivEditNotes.setOnClickListener {
            val nav = HomeFragmentDirections.actionHomeFragmentToEditFragment(listNote[position])
            Navigation.findNavController(holder.itemView).navigate(nav)
        }
        holder.binding.cardNotes.setOnClickListener{
            val nav = HomeFragmentDirections.actionHomeFragmentToDetailFragment2(listNote[position])
            Navigation.findNavController(holder.itemView).navigate(nav)
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setDataNote(list: List<Notes>){
        this.listNote = list
    }
}