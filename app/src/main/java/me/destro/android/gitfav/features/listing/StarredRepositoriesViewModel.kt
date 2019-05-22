package me.destro.android.gitfav.features.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import me.destro.android.libraries.github.model.StarredRepository
import me.destro.android.gitfav.features.listing.paging.StarredRepositoryFactory
import me.destro.android.libraries.github.GithubService

class StarredRepositoriesViewModel(private val githubService: GithubService): ViewModel() {
    private lateinit var mStarredRepository: LiveData<PagedList<StarredRepository>>

    fun getStarredRepositories(username: String): LiveData<PagedList<StarredRepository>> {
        this.mStarredRepository = LivePagedListBuilder(StarredRepositoryFactory(username, githubService), 20)
                .build()
        return this.mStarredRepository
    }
}
