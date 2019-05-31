package me.destro.android.gitfav

import me.destro.android.gitfav.data.repository.RemoteRepository
import me.destro.android.libraries.github.githubModules
import org.koin.dsl.module

private val koinModules = module {
    single { RemoteRepository(get()) }
}

val dataModules = arrayOf(githubModules, koinModules)