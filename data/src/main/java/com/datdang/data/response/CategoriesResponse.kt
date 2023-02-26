package com.datdang.data.response

import com.datdang.domain.model.category.CategoriesList
import com.datdang.domain.model.category.Category
import com.squareup.moshi.Json

data class CategoriesResponse(
    @Json(name = "categories") val data: List<CategoryDataResponse>,
    @Json(name = "totalCount") val totalCount: Int,
) {
    data class CategoryDataResponse(
        @Json(name = "_id") val id: String = "",
        @Json(name = "name") val name: String = "",
    ) {
        fun toCategoryData(): Category {
            return Category(
                id = this.id,
                name = this.name,
            )
        }
    }

    private fun toCategoryList(): List<Category> {
        return data.map { it.toCategoryData() }
    }

    fun toCategoriesList(): CategoriesList {
        return CategoriesList(
            categories = this.toCategoryList()
        )
    }
}