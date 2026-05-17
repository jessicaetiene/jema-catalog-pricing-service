package br.com.jema.catalog_pricing_service.api.promotion

import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import java.math.BigDecimal
import java.time.Instant

data class PromotionRequest(
    val productId: Long,
    val type: PromotionType,
    val value: BigDecimal,
    val startAt: Instant,
    val endAt: Instant,
    val active: Boolean,
    val priority: Int,
) {
}