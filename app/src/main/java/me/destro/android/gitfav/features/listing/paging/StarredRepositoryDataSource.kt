package me.destro.android.gitfav.features.listing.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import me.destro.android.gitfav.MainActivity
import me.destro.android.libraries.github.GithubService
import me.destro.android.libraries.github.model.StarredRepository
import me.destro.android.libraries.github.utilities.PageLinks
import retrofit2.Response
import java.util.regex.Pattern

class StarredRepositoryDataSource(private val username: String, private val githubService: GithubService) : PageKeyedDataSource<String, StarredRepository>() {


    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<String>, callback: PageKeyedDataSource.LoadInitialCallback<String, StarredRepository>) {
        val starredCall = githubService.listStarredRepository(this.username, 0)

        // TODO handling this disposable
        starredCall.subscribe({ response: Response<List<StarredRepository>> ->
            val starredRepositories = response.body()

            Log.d(MainActivity::class.java.name, response.headers().get("Link"))

            for (starredRepository in starredRepositories!!) {
                Log.d(MainActivity::class.java.name, starredRepository.name)
                Log.d(MainActivity::class.java.name, starredRepository.fullName)

                starredRepository.topics.forEach { Log.d(MainActivity::class.java.name, "topic: $it") }
            }

            val pageLinks = PageLinks(response.headers())

            callback.onResult(starredRepositories, pageLinks.prev, pageLinks.next)
        }, { t: Throwable ->

        })

    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<String>, callback: PageKeyedDataSource.LoadCallback<String, StarredRepository>) {

    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<String>, callback: PageKeyedDataSource.LoadCallback<String, StarredRepository>) {

        val p = Pattern.compile("page=(\\d+).*$")
        val m = p.matcher(params.key)
        var next: Int? = 0
        if (m.find()) {
            next = Integer.valueOf(m.group(1))
        }

        val starredCall = githubService.listStarredRepository(this.username, next!!)

        starredCall.subscribe({ response: Response<List<StarredRepository>> ->
            if (response.isSuccessful) {
                val starredRepositories = response.body()

                Log.d(MainActivity::class.java.name, response.headers().get("Link"))

                for (starredRepository in starredRepositories!!) {
                    Log.d(MainActivity::class.java.name, starredRepository.name)
                    Log.d(MainActivity::class.java.name, starredRepository.fullName)

                    starredRepository.topics.forEach { Log.d(MainActivity::class.java.name, "topic: $it") }
                }


                //StarredRepositoriesAdapter adapter = new StarredRepositoriesAdapter(starredRepositories);
                //rvStarredRepositories.setAdapter(adapter);
                //rvStarredRepositories.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                val pageLinks = PageLinks(response.headers())
                callback.onResult(starredRepositories, pageLinks.next)
            }
        }, { t: Throwable ->

        })
    }
}
