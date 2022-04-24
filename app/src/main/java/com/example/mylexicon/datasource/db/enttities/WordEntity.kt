package com.example.mylexicon.datasource.db.enttities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mylexicon.datasource.db.DBUtils.WORD_TABLE_NAME

@Entity(tableName = WORD_TABLE_NAME)
data class WordEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "translation") val translation: String?,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
    @ColumnInfo(name = "transcription") val transcription: String?
)