package me.destro.android.libraries.github.model

data class Permissions(
        val admin: Boolean,
        val pull: Boolean,
        val push: Boolean
)