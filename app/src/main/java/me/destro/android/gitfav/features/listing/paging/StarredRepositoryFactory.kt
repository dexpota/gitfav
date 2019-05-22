package me.destro.android.gitfav.features.listing.paging

import androidx.paging.DataSource

import me.destro.android.libraries.github.GithubService
import me.destro.android.libraries.github.model.StarredRepository

class StarredRepositoryFactory(private val username: String, private val githubService: GithubService) : DataSource.Factory<String, StarredRepository>() {

    override fun create(): DataSource<String, StarredRepository> {
        return StarredRepositoryDataSource(username, githubService)
    }
}
