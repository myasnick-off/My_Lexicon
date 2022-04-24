package com.example.mylexicon.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mylexicon.datasource.db.dao.WordDao
import com.example.mylexicon.datasource.db.enttities.WordEntity

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class LexiconDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao
}