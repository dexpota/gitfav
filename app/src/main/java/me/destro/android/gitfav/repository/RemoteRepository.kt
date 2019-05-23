package me.destro.android.gitfav.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import me.destro.android.libraries.github.GithubService
import me.destro.android.libraries.github.model.Repository
import retrofit2.Response

@Suppress("unused")
class RemoteRepository(private val githubService: GithubService) {

    fun getRepository(user: String, repository: String): Single<Response<Repository>> {
        val observable = githubService.getRepository(user, repository)
                .subscribeOn(Schedulers.io())

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