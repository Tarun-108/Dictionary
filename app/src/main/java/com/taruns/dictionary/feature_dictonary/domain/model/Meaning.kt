package com.taruns.dictionary.feature_dictonary.domain.model

import com.taruns.dictionary.feature_dictonary.data.remote.dto.DefinitionDto

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)
