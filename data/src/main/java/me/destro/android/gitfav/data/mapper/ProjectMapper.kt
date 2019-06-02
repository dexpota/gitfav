package me.destro.android.gitfav.data.mapper

import me.destro.android.libraries.github.model.Repository
import me.destro.android.libraries.github.model.StarredRepository

fun StarredRepository.asDomainModel() = me.destro.android.gitfav.domain.model.Repository(
        owner = owner.login,
        name = name,
        topics = topics,
        description = description,
        starsCount = stargazersCount,
        userTags = emptyArray(),
        languages = emptyArray()
)

fun Repository.asDomainModel() = me.destro.android.gitfav.domain.model.Repository(
        owner = owner.login,
        name = name,
        topics = topics,
        description = description,
        starsCount = stargazersCount,
        userTags = emptyArray(),
        languages = emptyArray()
)