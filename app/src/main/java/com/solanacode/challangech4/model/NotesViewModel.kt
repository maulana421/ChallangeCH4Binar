package com.solanacode.challangech4.model

import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.solanacode.challangech4.R

import com.solanacode.challangech4.room.NotesDao
import com.solanacode.challangech4.room.NotesDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotesViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var allNotes : MutableLiveData<List<Notes>>

    init{
        allNotes = MutableLiveData()
        getallNote()
    }

    fun getAllNoteObservers(): MutableLiveData<List<Notes>> {
        return allNotes
    }

    fun getallNote() {
        GlobalScope.launch {
            val userDao = NotesDatabase.getInstance(getApplication())!!.noteDao()
            val listnote = userDao.getDataNote()
            allNotes.postValue(listnote)
        }
    }
    fun insertNote(entity: Notes){
        val noteDao = NotesDatabase.getInstance(getApplication())?.noteDao()
        noteDao!!.insertNote(entity)
        getallNote()
    }

    fun deleteNote(entity: Notes){
        val userDao = NotesDatabase.getInstance(getApplication())!!.noteDao()
        userDao?.deleteNote(entity)
        getallNote()
    }

    fun updateNote(entity: Notes){
        val userDao = NotesDatabase.getInstance(getApplication())!!.noteDao()
        userDao?.updateNote(entity)
        getallNote()
    }

    fun deleteAlert(context: Context, note :Notes, deleteText : String){
        val alert = AlertDialog.Builder(context)
        alert.apply {
            setTitle("")
            setMessage("Apakah Anda Akan Menghapus Catatan ini?")
            setPositiveButton(deleteText){ dialogInterface: DialogInterface, i: Int ->
                deleteNote(note)

            }
            setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int -> }
        }
        alert.show()

    }





}