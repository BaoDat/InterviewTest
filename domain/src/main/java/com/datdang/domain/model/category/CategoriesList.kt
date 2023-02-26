package com.datdang.domain.model.category

import java.io.Serializable

data class CategoriesList(
        var categories: List<Category> = arrayListOf(),
        var totalCount: Int = 0
) : Serializable
