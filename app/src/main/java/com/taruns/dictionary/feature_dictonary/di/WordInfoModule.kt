package com.taruns.dictionary.feature_dictonary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.taruns.dictionary.feature_dictonary.data.local.WordInfoDao
import com.taruns.dictionary.feature_dictonary.data.local.WordInfoDatabase
import com.taruns.dictionary.feature_dictonary.data.remote.DictionaryApi
import com.taruns.dictionary.feature_dictonary.data.util.GsonParser
import com.taruns.dictionary.feature_dictonary.data.util.JsonParser
import com.taruns.dictionary.feature_dictonary.domain.repository.WordInfoRepository
import com.taruns.dictionary.feature_dictonary.domain.repository.WordInfoRepositoryImpl
import com.taruns.dictionary.feature_dictonary.domain.use_case.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo{
        return  GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(api: DictionaryApi, dao: WordInfoDao): WordInfoRepository{
        return  WordInfoRepositoryImpl(
            api = api,
            dao = dao
        )
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase{
        return  Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "words_DB"
        ).addTypeConverter(GsonParser(Gson())).build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi{
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

}