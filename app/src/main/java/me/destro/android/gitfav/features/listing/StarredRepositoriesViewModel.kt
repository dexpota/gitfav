package me.destro.android.gitfav.features.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.destro.android.gitfav.data.repository.RemoteRepository
import me.destro.android.gitfav.domain.model.Repository

import me.destro.android.gitfav.features.listing.paging.StarredRepositoryFactory

class StarredRepositoriesViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {
    private lateinit var mStarredRepository: LiveData<PagedList<Repository>>

    fun getStarredRepositories(username: String): LiveData<PagedList<Repository>> {
        this.mStarredRepository = LivePagedListBuilder(StarredRepositoryFactory(username, remoteRepository), 20)
                .build()
        return this.mStarredRepository
    }
}
