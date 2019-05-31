package me.destro.android.gitfav.features.listing.paging

import androidx.paging.DataSource
import me.destro.android.gitfav.data.repository.RemoteRepository
import me.destro.android.gitfav.domain.model.Repository


class StarredRepositoryFactory(private val username: String, private val remote: RemoteRepository) : DataSource.Factory<String, Repository>() {

    override fun create(): DataSource<String, Repository> {
        return StarredRepositoryDataSource(username, remote)
    }
}
