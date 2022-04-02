package com.example.mylexicon.di.component

import com.example.mylexicon.di.module.InteractorModule
import com.example.mylexicon.di.module.NetworkModule
import com.example.mylexicon.di.module.RepositoryModule
import com.example.mylexicon.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)

@Singleton
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
}
