package br.com.jema.catalog_pricing_service.api.promotion


import br.com.jema.catalog_pricing_service.domain.Promotion


fun Promotion.toPromotionResponse() = PromotionResponse(
    id = id,
    productId = product.id,
    type = type,
    value = value,
    startAt = startAt,
    endAt = endAt,
    active = active,
    priority = priority,
    createdAt = createdAt,
    updateAt = updatedAt
)