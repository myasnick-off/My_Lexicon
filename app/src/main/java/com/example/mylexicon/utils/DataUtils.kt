package com.example.mylexicon.utils

import com.example.core.model.Meaning2
import com.example.core.model.Translation
import com.example.core.model.Word
import com.example.core.ui.model.UiWord
import com.example.mylexicon.datasource.db.enttities.WordEntity

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

fun wordModelToUiConvert(word: Word): UiWord {
    return UiWord(
        id = word.id,
        word = word.text.orEmpty(),
        translation = word.meanings?.first()?.translation?.text.orEmpty(),
        note = word.meanings?.first()?.translation?.note.orEmpty(),
        imageUrl = word.meanings?.first()?.imageUrl,
        transcription = word.meanings?.first()?.transcription.orEmpty()
    )
}