package me.destro.android.gitfav.features.listing.paging

import androidx.paging.PageKeyedDataSource
import me.destro.android.gitfav.data.repository.RemoteRepository
import me.destro.android.gitfav.domain.model.Repository
import me.destro.android.gitfav.utilities.PageLinks
import retrofit2.Response
import java.util.regex.Pattern

class StarredRepositoryDataSource(private val username: String, private val githubService: RemoteRepository) : PageKeyedDataSource<String, Repository>() {


    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<String>, callback: PageKeyedDataSource.LoadInitialCallback<String, Repository>) {
        val starredCall = githubService.listStarredRepository(this.username, 0)

        // TODO handling this disposable
        starredCall.subscribe({ response: Response<List<Repository>> ->
            val starredRepositories = response.body()

            val pageLinks = PageLinks(response.headers())

            starredRepositories?.let { callback.onResult(starredRepositories, pageLinks.prev, pageLinks.next) }

        }, { t: Throwable ->

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

        starredCall.subscribe({ response: Response<List<Repository>> ->
            if (response.isSuccessful) {
                val starredRepositories = response.body()

                val pageLinks = PageLinks(response.headers())

                starredRepositories?.let { callback.onResult(starredRepositories, pageLinks.next) }
            }
        }, { t: Throwable ->

        })
    }
}
