package com.taruns.dictionary.feature_dictonary.data.remote

import com.taruns.dictionary.feature_dictonary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word:String
    ): List<WordInfoDto>

}