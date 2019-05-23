package me.destro.android.gitfav.data.mapper

import me.destro.android.gitfav.domain.model.Project
import me.destro.android.libraries.github.model.Repository
import me.destro.android.libraries.github.model.StarredRepository

fun StarredRepository.asDomainModel() = Project(
        owner = this.owner.login,
        name = this.name,
        topics = this.topics
)

fun Repository.asDomainModel() = Project(
        owner = this.owner.login,
        name = this.name,
        topics = this.topics
)