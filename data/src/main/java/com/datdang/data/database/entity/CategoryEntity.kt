package com.datdang.data.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.datdang.data.database.entity.CategoryEntity.Companion.CATEGORY_TABLE
import com.datdang.domain.model.category.Category
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = CATEGORY_TABLE)
data class CategoryEntity(

    @PrimaryKey
    @ColumnInfo(name = CATEGORY_ID)
    var categoryId: String,

    @ColumnInfo(name = CATEGORY_NAME)
    var categoryName: String,


    @ColumnInfo(name = CATEGORY_SELECTED)
    var categorySelected: Boolean

) : Parcelable {

    fun toCategoryData(): Category {
        return Category(
            id = categoryId,
            name = categoryName,
            isSelected = categorySelected
        )
    }

    companion object {
        const val CATEGORY_TABLE = "category"
        const val CATEGORY_ID = "category_id"
        const val CATEGORY_NAME = "category_name"
        const val CATEGORY_SELECTED = "category_selected"


        fun toCategoryEntity(categoryCard: Category): CategoryEntity {
            return CategoryEntity(
                categoryCard.id,
                categoryCard.name,
                categoryCard.isSelected,
            )
        }
    }
}
