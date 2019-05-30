package me.destro.android.gitfav.domain.errors

import java.lang.Exception

class NetworkDataSourceException(errorBody: String? = null) : Exception(errorBody)