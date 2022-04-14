package com.example.mylexicon.utils

import com.example.mylexicon.datasource.db.enttities.WordEntity
import com.example.mylexicon.model.Meaning2
import com.example.mylexicon.model.Translation
import com.example.mylexicon.model.Word

fun wordEntityToModelConvert(wordEntity: WordEntity): Word {
    return Word(
        id = wordEntity.id,
        text = wordEntity.word,
        meanings = listOf(
            Meaning2(
                id = 0,
                translation = Translation(text = wordEntity.translation, note = null),
                imageUrl = wordEntity.imageUrl,
                transcription = wordEntity.transcription,
                soundUrl = null
            )
        )
    )
}

fun wordModelToEntityConvert(word: Word): WordEntity {
    return WordEntity(
        id = word.id,
        word = word.text.orEmpty(),
        translation = word.meanings?.first()?.translation?.text,
        imageUrl = word.meanings?.first()?.imageUrl,
        transcription = word.meanings?.first()?.transcription
    )
}