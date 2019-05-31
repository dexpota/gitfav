package me.destro.android.gitfav.persistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey
    var categoryName: String
)