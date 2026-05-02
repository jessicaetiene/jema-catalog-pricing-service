package br.com.jema.catalog_pricing_service.api.product

import br.com.jema.catalog_pricing_service.domain.Product

fun Product.toResponse() : ProductResponse =
    ProductResponse(
        id = id,
        name = name,
        description = description,
        price = price,
        active = active,
        createdAt = createdAt,
        updateAt = updatedAt
    )