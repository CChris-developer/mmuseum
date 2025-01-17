package com.example.metropolitanmuseum.utils

import com.example.metropolitanmuseum.mvvm.ArtObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitArt {
        @GET("public/collection/v1/objects/{id}")
        suspend fun getObject(@Path("id") id: Int): Response<ArtObject>
}