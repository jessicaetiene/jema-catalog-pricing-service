package br.com.jema.catalog_pricing_service.domain

import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import java.math.BigDecimal
import java.time.Instant

class Promotion(
    val id: Long? = null,
    val productId: Long,
    val type: PromotionType,
    val value: BigDecimal,
    val startAt: Instant,
    val endAt: Instant,
    val active: Boolean,
    val priority: Int
) {
}
