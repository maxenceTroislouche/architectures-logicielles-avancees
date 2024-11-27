package com.formation.myecommerceapp.ui.page.product.details.state

import java.util.UUID

data class ProductDetails(
    val id: UUID,
    val name: String,
    val description: String,
    val price: Double,
    val isAvailable: Boolean,
    val averageRate: Double,
    val rateCount: Int,
    val category: String,
)