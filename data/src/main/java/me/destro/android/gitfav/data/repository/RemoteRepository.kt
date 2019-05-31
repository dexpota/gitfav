package me.destro.android.gitfav.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import me.destro.android.gitfav.data.Paged
import me.destro.android.gitfav.data.mapper.asDomainModel
import me.destro.android.gitfav.domain.errors.NetworkDataSourceException
import me.destro.android.gitfav.domain.model.Repository
import me.destro.android.libraries.github.GithubService
import me.destro.android.libraries.github.utilities.PageLinks
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

@Suppress("unused")
class RemoteRepository(private val githubService: GithubService) {

    fun listStarredRepository(@Path("user") user: String, @Query("page") page: Int):
            Single<Result<Paged<List<Repository>>>> {
        val observable = githubService.listStarredRepository(user, page)
                .subscribeOn(Schedulers.io())
                .map { response ->
                    if (response.isSuccessful) {

                        val repository = response.body()

                        val pageLinks = PageLinks(response.headers())


                        if (repository != null) {
                            val content = Paged(repository.map { it.asDomainModel() }, pageLinks.next, pageLinks.prev)
                            Result.success(content)
                        }else {
                            Result.failure(NetworkDataSourceException())
                        }
                    } else {
                        Result.failure(NetworkDataSourceException())
                    }
                }
        return observable
    }


    fun getRepository(user: String, repository: String): Single<Result<Repository>> {
        val observable = githubService.getRepository(user, repository)
                .subscribeOn(Schedulers.io())
                .map { response ->
                    if (response.isSuccessful) {

                        val repository = response.body()

                        if (repository != null) {
                            Result.success(repository.asDomainModel())
                        }else {
                            Result.failure(NetworkDataSourceException())
                        }
                    }else {
                        Result.failure(NetworkDataSourceException(response.errorBody()?.string()))
                    }
                }

        return observable
    }

    fun getRepositoryTopics(user: String, repository: String): Single<Map<String, Int>> {
        val observable = githubService.listRepositoryTopics(user, repository)
                .subscribeOn(Schedulers.io())


        return observable
    }

    fun getRepositoryLanguages(user: String, repository: String): Single<MutableMap<String, Int>> {
        val observable = githubService.listRepositoryLanguages(user, repository)
                .subscribeOn(Schedulers.io())

        return observable
    }

}