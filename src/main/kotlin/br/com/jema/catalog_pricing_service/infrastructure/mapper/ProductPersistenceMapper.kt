package br.com.jema.catalog_pricing_service.infrastructure.mapper

import br.com.jema.catalog_pricing_service.domain.Product
import br.com.jema.catalog_pricing_service.domain.entity.ProductEntity

fun Product.toEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    description = description,
    price = price,
    active = active,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun ProductEntity.toDomain(): Product = Product(
    id = id,
    name = name,
    description = description,
    price = price,
    active = active,
    createdAt = createdAt,
    updatedAt = updatedAt
)