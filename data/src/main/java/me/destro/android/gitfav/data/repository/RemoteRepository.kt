package me.destro.android.gitfav.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import me.destro.android.gitfav.data.mapper.asDomainModel
import me.destro.android.gitfav.domain.errors.NetworkDataSourceException
import me.destro.android.gitfav.domain.model.Repository
import me.destro.android.libraries.github.GithubService

@Suppress("unused")
class RemoteRepository(private val githubService: GithubService) {

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