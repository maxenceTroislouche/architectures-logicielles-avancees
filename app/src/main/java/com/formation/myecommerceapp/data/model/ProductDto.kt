package com.formation.myecommerceapp.data.model

import java.util.UUID

data class ProductDto(
    val id: UUID,
    val name: String,
    val description: String,
    val price: Double,
    val isAvailable: Boolean,
    val averageRate: Double,
    val rateCount: Int,
    val category: String,
)