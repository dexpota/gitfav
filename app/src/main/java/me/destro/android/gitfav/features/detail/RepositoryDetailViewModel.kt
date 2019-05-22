package me.destro.android.gitfav.features.detail

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.destro.android.gitfav.GitfavApplication
import me.destro.android.libraries.github.GithubService

class RepositoryDetailViewModel(val githubService: GithubService): ViewModel() {

    fun getRepository(user: String, repo: String) =
            githubService.getRepository(user, repo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}