package br.com.jema.catalog_pricing_service.infrastructure.mapper
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.entity.PromotionEntity

fun Promotion.toEntity(): PromotionEntity = PromotionEntity(
    id = id,
    product = product.toEntity(),
    type = type,
    value = value,
    startAt = startAt,
    endAt = endAt,
    active = active,
    priority = priority,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun PromotionEntity.toDomain(): Promotion = Promotion(
    id = id,
    product = product.toDomain(),
    type = type,
    value = value,
    startAt = startAt,
    endAt = endAt,
    active = active,
    priority = priority,
    createdAt = createdAt,
    updatedAt = updatedAt
)