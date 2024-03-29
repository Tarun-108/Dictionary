package com.taruns.dictionary.feature_dictonary.domain.use_case

import com.taruns.dictionary.core.util.Resource
import com.taruns.dictionary.feature_dictonary.domain.model.WordInfo
import com.taruns.dictionary.feature_dictonary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>>{
        if(word.isBlank()){
            return flow{ }
        }
        return repository.getWordInfo(word)
    }

}