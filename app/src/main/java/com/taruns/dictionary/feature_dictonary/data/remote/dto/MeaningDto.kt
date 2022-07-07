package com.taruns.dictionary.feature_dictonary.data.remote.dto

import com.taruns.dictionary.feature_dictonary.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
){
    fun toMeaning() : Meaning{
        return Meaning(
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}