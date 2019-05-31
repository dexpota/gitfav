package me.destro.android.gitfav.persistence.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import me.destro.android.gitfav.persistence.entities.Category

@Dao
interface CategoryDao {
    @Query("SELECT * from category")
    fun all(): List<Category>

    @Insert
    fun insert(vararg categories: Category)

    @Delete
    fun delete(category: Category)
}