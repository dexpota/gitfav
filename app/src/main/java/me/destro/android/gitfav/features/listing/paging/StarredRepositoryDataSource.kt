package me.destro.android.gitfav.features.listing.paging

import androidx.paging.PageKeyedDataSource
import me.destro.android.gitfav.data.Paged
import me.destro.android.gitfav.data.repository.RemoteRepository
import me.destro.android.gitfav.domain.model.Repository
import java.util.regex.Pattern

class StarredRepositoryDataSource(private val username: String, private val githubService: RemoteRepository) : PageKeyedDataSource<String, Repository>() {


    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<String>, callback: PageKeyedDataSource.LoadInitialCallback<String, Repository>) {
        val starredCall = githubService.listStarredRepository(this.username, 0)

        // TODO handling this disposable
        starredCall.subscribe({ response: Result<Paged<List<Repository>>> ->
            if (response.isSuccess) {
                val pagedResponse = response.getOrThrow()

                val next = pagedResponse.next
                val prev = pagedResponse.previous
                val starredRepositories = pagedResponse.response

                callback.onResult(starredRepositories, prev, next)
            }

        }, {

        })

    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<String>, callback: PageKeyedDataSource.LoadCallback<String, Repository>) {

    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<String>, callback: PageKeyedDataSource.LoadCallback<String, Repository>) {

        val p = Pattern.compile("page=(\\d+).*$")
        val m = p.matcher(params.key)
        var next: Int? = 0
        if (m.find()) {
            next = Integer.valueOf(m.group(1))
        }

        val starredCall = githubService.listStarredRepository(this.username, next!!)

        // TODO handling this disposable
        starredCall.subscribe({ response: Result<Paged<List<Repository>>> ->
            if (response.isSuccess) {
                val pagedResponse = response.getOrThrow()

                val nextLink = pagedResponse.next
                val starredRepositories = pagedResponse.response

                callback.onResult(starredRepositories, nextLink)
            }
        }, {

        })
    }
}
