package me.destro.android.gitfav.data

data class Paged<T>(val response: T,
                    val next: String?,
                    val previous: String?)