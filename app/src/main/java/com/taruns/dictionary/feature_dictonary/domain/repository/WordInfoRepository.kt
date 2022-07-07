package com.taruns.dictionary.feature_dictonary.domain.repository

import com.taruns.dictionary.core.util.Resource
import com.taruns.dictionary.feature_dictonary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>

}