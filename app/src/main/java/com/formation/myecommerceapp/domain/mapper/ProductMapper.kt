package com.formation.myecommerceapp.domain.mapper

import com.formation.myecommerceapp.data.model.ProductDto
import com.formation.myecommerceapp.ui.page.product.details.state.ProductDetails
import com.formation.myecommerceapp.ui.page.product.list.state.Product

fun ProductDto.toProduct() = Product(
    id = id,
    name = name,
    description = description,
    isAvailable = isAvailable,
    price = price,
)

fun ProductDto.toProductDetails() = ProductDetails(
    id = id,
    name = name,
    description = description,
    price = price,
    isAvailable = isAvailable,
    averageRate = averageRate,
    rateCount = rateCount,
    category = category,
)
