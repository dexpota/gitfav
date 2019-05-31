package me.destro.android.gitfav.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repository(
    @PrimaryKey
    var url: String,

    @ColumnInfo(name = "topics")
    var topics: String
)