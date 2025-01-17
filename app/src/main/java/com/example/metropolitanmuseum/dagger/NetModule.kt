package com.example.metropolitanmuseum.dagger

import com.example.metropolitanmuseum.utils.Consts.BASE_URL
import com.example.metropolitanmuseum.utils.RetrofitArt
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideGson(): Gson? {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.serializeNulls().create()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        })
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitArt(gson: Gson?, okHttpClient: OkHttpClient): RetrofitArt {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson!!))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(RetrofitArt::class.java)
    }
}