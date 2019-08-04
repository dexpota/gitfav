package me.destro.android.gitfav.features.detail

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import me.destro.android.gitfav.data.repository.RemoteRepository

class RepositoryDetailViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    fun getRepository(user: String, repo: String) =
            remoteRepository.getRepository(user, repo)
                    .observeOn(AndroidSchedulers.mainThread())
}