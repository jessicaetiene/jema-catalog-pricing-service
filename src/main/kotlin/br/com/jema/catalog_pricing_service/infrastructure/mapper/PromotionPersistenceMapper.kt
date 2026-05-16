package br.com.jema.catalog_pricing_service.infrastructure.mapper
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.entity.PromotionEntity

fun Promotion.toEntity(): PromotionEntity = PromotionEntity(
    id = id,
    productId = productId,
    type = type,
    value = value,
    startAt = startAt,
    endAt = endAt,
    active = active,
    priority = priority
)

fun PromotionEntity.toDomain(): Promotion = Promotion(
    id = id,
    productId = productId,
    type = type,
    value = value,
    startAt = startAt,
    endAt = endAt,
    active = active,
    priority = priority
)