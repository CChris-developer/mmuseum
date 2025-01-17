package com.example.metropolitanmuseum.dagger

import com.example.metropolitanmuseum.mvvm.ArtRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetModule::class, AppModule::class])
interface DaggerComponent {

    fun getArtRepository(): ArtRepository
}
