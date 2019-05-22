package me.destro.android.gitfav

import android.app.Application

import com.squareup.moshi.Moshi
import me.destro.android.gitfav.koin.koinModules

import me.destro.android.libraries.github.GithubService
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class GitfavApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(koinModules)
        }
    }


}
