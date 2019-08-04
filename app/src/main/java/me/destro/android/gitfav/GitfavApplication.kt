package me.destro.android.gitfav

import android.app.Application

import me.destro.android.gitfav.koin.koinModules

import org.koin.core.context.startKoin

@SuppressWarnings("unused")
class GitfavApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(koinModules)
            modules(dataModules.asIterable())
        }
    }
}
