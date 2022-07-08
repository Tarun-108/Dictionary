package com.taruns.dictionary.feature_dictonary.domain.repository

import com.taruns.dictionary.core.util.Resource
import com.taruns.dictionary.feature_dictonary.data.local.WordInfoDao
import com.taruns.dictionary.feature_dictonary.data.remote.DictionaryApi
import com.taruns.dictionary.feature_dictonary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try{
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfo(remoteWordInfos.map { it.toWordInfoEntity() })

        }catch (e: HttpException){

            emit(Resource.Error(
                message = "Oops, Something went wrong!",
                data = wordInfos
            ))

        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your connection.",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}