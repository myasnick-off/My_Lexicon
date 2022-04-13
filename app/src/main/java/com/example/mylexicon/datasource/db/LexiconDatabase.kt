package com.example.mylexicon.datasource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mylexicon.datasource.db.dao.MeaningDao
import com.example.mylexicon.datasource.db.dao.WordDao
import com.example.mylexicon.datasource.db.enttities.MeaningEntity
import com.example.mylexicon.datasource.db.enttities.WordEntity

@Database(entities = [WordEntity::class, MeaningEntity::class], version = 1, exportSchema = false)
abstract class LexiconDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun meaningDao(): MeaningDao

    companion object {
        private const val  DB_NAME = "database.db"
        private var instance: LexiconDatabase? = null

        fun getInstance(context: Context): LexiconDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, LexiconDatabase::class.java, DB_NAME).build()
            }
            return instance!!
        }
    }
}