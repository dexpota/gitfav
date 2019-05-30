package me.destro.android.gitfav.koin

import com.squareup.moshi.Moshi
import me.destro.android.gitfav.features.detail.RepositoryDetailViewModel
import me.destro.android.gitfav.features.listing.StarredRepositoriesViewModel
import me.destro.android.gitfav.data.repository.RemoteRepository
import me.destro.android.libraries.github.GithubService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val koinModules = module {
    single {
        Moshi.Builder().build()
    }

    single {
        val url = "https://api.github.com"

        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .build()

        retrofit
    }

    single {
        get<Retrofit>().create(GithubService::class.java)
    }

    single { RemoteRepository(get()) }

    viewModel { RepositoryDetailViewModel(get()) }

    viewModel { StarredRepositoriesViewModel(get()) }
}