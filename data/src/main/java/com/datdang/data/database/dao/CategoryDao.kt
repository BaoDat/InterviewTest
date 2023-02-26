package com.datdang.data.database.dao

import androidx.room.*
import com.datdang.data.database.entity.CategoryEntity
import com.datdang.data.database.entity.CategoryEntity.Companion.CATEGORY_SELECTED

@Dao
interface CategoryDao {
  @Query("SELECT * FROM category WHERE $CATEGORY_SELECTED=1")
  suspend fun getSelectedCategories() : List<CategoryEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSelectedCategory(category: CategoryEntity)

}