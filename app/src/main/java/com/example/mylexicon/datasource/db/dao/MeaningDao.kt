package com.example.mylexicon.datasource.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylexicon.datasource.db.DBUtils.MEANING_TABLE_NAME
import com.example.mylexicon.datasource.db.enttities.MeaningEntity

@Dao
interface MeaningDao {

    @Query("SELECT * FROM $MEANING_TABLE_NAME WHERE word_id = :wordId")
    suspend fun getAll(wordId: Int): List<MeaningEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(meanings: List<MeaningEntity>)
}