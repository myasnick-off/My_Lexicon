package com.example.mylexicon

import com.example.core.model.Meaning2
import com.example.core.model.Translation
import com.example.core.model.Word
import com.example.mylexicon.datasource.db.enttities.WordEntity
import com.example.mylexicon.utils.ModelsMapper
import org.junit.Assert.*
import org.junit.Test


class ModelsMapperTest {

    @Test
    fun modelsMapper_wordEntityToModelConvert_ReturnsNotNull() {
        val wordEntity = WordEntity(
            id = 0,
            word = "",
            translation = null,
            imageUrl = null,
            transcription = null
        )
        assertNotNull(ModelsMapper().wordEntityToModelConvert(wordEntity))
    }

    @Test
    fun modelsMapper_wordEntityToModelConvert_ReturnsEqualModel() {
        val wordEntity = WordEntity(
            id = 0,
            word = "some word",
            translation = "translation",
            imageUrl = "img/url",
            transcription = "[transcription]"
        )
        val word = Word(
            id = 0,
            text = "some word",
            meanings = listOf(
                Meaning2(
                    id = 0,
                    translation = Translation(text = "translation", note = null),
                    imageUrl = "img/url",
                    transcription = "[transcription]",
                    soundUrl = null
                )
            )
        )
        assertEquals(ModelsMapper().wordEntityToModelConvert(wordEntity), word)
    }

    @Test
    fun modelsMapper_wordEntityToModelConvert_ReturnsNullField() {
        val wordEntity = WordEntity(
            id = 0,
            word = "some word",
            translation = "translation",
            imageUrl = "img/url",
            transcription = "[transcription]"
        )
        val word = ModelsMapper().wordEntityToModelConvert(wordEntity)
        assertNull(word.meanings?.first()?.translation?.note)
    }

    @Test
    fun modelsMapper_WordModelToEntityConvert_ReturnsNotEqualEntity() {
        val word = Word(
            id = 0,
            text = "some word",
            meanings = listOf(
                Meaning2(
                    id = 0,
                    translation = Translation(text = "translation", note = null),
                    imageUrl = "img/url",
                    transcription = "[transcription]",
                    soundUrl = null
                )
            )
        )
        val wordEntity = WordEntity(
            id = 0,
            word = "",
            translation = "",
            imageUrl = "",
            transcription = ""
        )
        assertNotEquals(ModelsMapper().wordModelToEntityConvert(word), wordEntity)
    }

    @Test
    fun modelsMapper_WordModelToEntityConvert_ReturnsNotNullField() {
        val word = Word(
            id = 0,
            text = null,
            meanings = listOf(
                Meaning2(
                    id = 0,
                    translation = Translation(text = "translation", note = null),
                    imageUrl = "img/url",
                    transcription = null,
                    soundUrl = null
                )
            )
        )
        val wordEntity = ModelsMapper().wordModelToEntityConvert(word)
        assertNotNull(wordEntity.word)
    }

    @Test
    fun modelsMapper_WordModelToEntityConvert_ReturnsSameFields() {
        val word = Word(
            id = 0,
            text = "some word",
            meanings = listOf(
                Meaning2(
                    id = 0,
                    translation = Translation(text = "translation", note = null),
                    imageUrl = "img/url",
                    transcription = null,
                    soundUrl = null
                )
            )
        )
        val wordEntity = ModelsMapper().wordModelToEntityConvert(word)
        assertSame(word.text, wordEntity.word)
    }
}