package com.example.mylexicon.datasource.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylexicon.datasource.db.DBUtils.WORD_TABLE_NAME
import com.example.mylexicon.datasource.db.enttities.WordEntity

@Dao
interface WordDao {

    @Query("SELECT * FROM $WORD_TABLE_NAME")
    suspend fun getAll(): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: WordEntity)
}