package me.destro.android.gitfav.features.detail

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.destro.android.gitfav.GitfavApplication

class RepositoryDetailViewModel : ViewModel() {
    fun getRepository(user: String, repo: String) =
            GitfavApplication.githubService.getRepository(user, repo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}