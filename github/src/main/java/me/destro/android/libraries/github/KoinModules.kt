package me.destro.android.libraries.github

import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val githubModules = module {
    single {
        Moshi.Builder().build()
    }

    single {
        val url = "https://api.github.com"

        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
                .build()

        retrofit
    }

    single {
        get<Retrofit>().create(GithubService::class.java)
    }
}