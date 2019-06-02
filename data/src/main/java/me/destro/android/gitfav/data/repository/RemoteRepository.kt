package me.destro.android.gitfav.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import me.destro.android.gitfav.data.Paged
import me.destro.android.gitfav.data.mapper.asDomainModel
import me.destro.android.gitfav.domain.errors.NetworkDataSourceException
import me.destro.android.gitfav.domain.model.Repository
import me.destro.android.libraries.github.GithubService
import me.destro.android.libraries.github.utilities.PageLinks
import java.io.IOException
import java.lang.Exception
import com.github.kittinunf.result.Result as Result

@Suppress("unused")
class RemoteRepository(private val githubService: GithubService) {

    fun listStarredRepository(user: String, page: Int):
            Single<Result<Paged<List<Repository>>, Exception>> {
        return githubService.listStarredRepository(user, page)
                .subscribeOn(Schedulers.io())
                .map { response ->
                    if (response.isSuccessful) {

                        val repository = response.body()

                        val pageLinks = PageLinks(response.headers())


                        if (repository != null) {
                            val content = Paged(repository.map { it.asDomainModel() }, pageLinks.next, pageLinks.prev)
                            Result.success(content)
                        }else {
                            Result.error(NetworkDataSourceException())
                        }
                    } else {
                        Result.error(NetworkDataSourceException())
                    }
                }
    }


    fun error():Exception {
        return NetworkDataSourceException()
    }


    fun getRepository(user: String, repo: String): Single<Result<Repository, Exception>> {
        return githubService.getRepository(user, repo)
                .subscribeOn(Schedulers.io())
                .map { response ->
                    if (response.isSuccessful) {

                        val repository = response.body()

                        if (repository != null) {
                            Result.success(repository.asDomainModel())
                        }else {
                            Result.error(NetworkDataSourceException())
                        }
                    }else {
                        Result.error(NetworkDataSourceException(response.errorBody()?.string()))
                    }
                }
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