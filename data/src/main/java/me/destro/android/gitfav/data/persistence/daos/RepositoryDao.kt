package me.destro.android.gitfav.data.persistence.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import me.destro.android.gitfav.data.persistence.entities.Repository

@Dao
interface RepositoryDao {
    @Query("SELECT * from repository")
    fun all(): List<Repository>

    @Insert
    fun insert(vararg repositories: Repository)

    @Delete
    fun delete(repository: Repository)
}