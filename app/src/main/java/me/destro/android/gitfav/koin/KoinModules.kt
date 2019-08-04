package me.destro.android.gitfav.koin

import me.destro.android.gitfav.features.detail.RepositoryDetailViewModel
import me.destro.android.gitfav.features.listing.StarredRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModules = module {

    viewModel { RepositoryDetailViewModel(get()) }

    viewModel { StarredRepositoriesViewModel(get()) }
}