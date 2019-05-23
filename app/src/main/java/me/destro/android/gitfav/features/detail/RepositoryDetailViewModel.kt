package me.destro.android.gitfav.features.detail

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.destro.android.gitfav.GitfavApplication
import me.destro.android.gitfav.repository.RemoteRepository
import me.destro.android.libraries.github.GithubService

class RepositoryDetailViewModel(private val remoteRepository: RemoteRepository): ViewModel() {

    fun getRepository(user: String, repo: String) =
            remoteRepository.getRepository(user, repo)
                    .observeOn(AndroidSchedulers.mainThread())
}