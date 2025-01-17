package com.example.metropolitanmuseum.dagger

import com.example.metropolitanmuseum.mvvm.ArtRepository
import com.example.metropolitanmuseum.utils.RetrofitArt
import dagger.Module
import dagger.Provides

@Module

class AppModule {
    @Provides
    fun provideArtRepository(retrofit: RetrofitArt): ArtRepository =
        ArtRepository(retrofit)
}
