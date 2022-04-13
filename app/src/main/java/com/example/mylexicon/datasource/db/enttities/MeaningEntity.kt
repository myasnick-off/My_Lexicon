package com.example.mylexicon.datasource.db.enttities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mylexicon.datasource.db.DBUtils.MEANING_TABLE_NAME
import com.example.mylexicon.model.Translation

@Entity(
    tableName = MEANING_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = WordEntity::class,
            parentColumns = ["id"],
            childColumns = ["word_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MeaningEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "word_id") val wordId: Int,
    @ColumnInfo(name = "translation") val translation: String?,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
    @ColumnInfo(name = "transcription") val transcription: String?,
    @ColumnInfo(name = "sound_url") val soundUrl: String?
)