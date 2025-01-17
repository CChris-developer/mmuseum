package com.example.metropolitanmuseum.mvvm

import com.example.metropolitanmuseum.utils.RetrofitArt
import retrofit2.Response

class ArtRepository(private val retrofit: RetrofitArt) {

    suspend fun getArtObject(id: Int) : Response<ArtObject>? {
      return  try {
            retrofit.getObject(id)
        }
      catch (e: Exception) {
          return null
      }
    }
}