package com.solanacode.challangech4.room

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.solanacode.challangech4.model.Notes
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Notes::class], version = 1 , exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun noteDao() : NotesDao

    companion object {

        @Volatile private var instance : NotesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: kotlin.synchronized(LOCK) {
            instance ?: getInstance(context).also {
                instance = it
            }
        }

        fun getInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NotesDatabase::class.java,
            "Prediction"
        ).build()

    }
}

//fun getdbInstance(context: Context): NotesDatabase{
//    val tempInstance= NotesDatabase.instance
//    if(tempInstance!= null) {
//        return tempInstance
//    }
//    kotlin.synchronized(this) {
//        val roomdbInstance = Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").build()
//        NotesDatabase.instance = roomdbInstance
//        return roomdbInstance
//    }
//}



//companion object {
//
//        @Volatile private var instance : NotesDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: kotlin.synchronized(LOCK){
//            instance ?: buildDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            NotesDatabase::class.java,
//            "app_with_room.db"
//        ).build()
//
//    }