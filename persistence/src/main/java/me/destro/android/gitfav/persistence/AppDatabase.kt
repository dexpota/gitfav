package me.destro.android.gitfav.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import me.destro.android.gitfav.persistence.daos.CategoryDao
import me.destro.android.gitfav.persistence.daos.RepositoryDao
import me.destro.android.gitfav.persistence.entities.Category
import me.destro.android.gitfav.persistence.entities.Repository

@Database(entities = [Repository::class, Category::class], version = 1)
abstract class RepositoryDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
    abstract fun categoryDao(): CategoryDao
}