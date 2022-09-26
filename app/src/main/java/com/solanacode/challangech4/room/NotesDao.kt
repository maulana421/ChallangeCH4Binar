package com.solanacode.challangech4.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.solanacode.challangech4.model.Notes


@Dao
interface NotesDao {
    @Insert
    fun insertNote(notes: Notes)

    @Query("SELECT * FROM Notes")
    fun getDataNote() : MutableList<Notes>

    @Delete
    fun deleteNote(notes: Notes)

    @Update
    fun updateNote(notes: Notes)

}