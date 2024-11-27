package com.formation.myecommerceapp.ui.page.product.list.state

import java.util.UUID

data class Product(
    val id: UUID,
    val name: String,
    val description: String,
    val isAvailable: Boolean,
    val price: Double
)
