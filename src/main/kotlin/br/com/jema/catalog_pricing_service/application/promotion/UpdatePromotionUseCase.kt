package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.shared.exception.PromotionNotFoundException
import java.math.BigDecimal
import java.time.Instant
import kotlin.Long

class UpdatePromotionUseCase(
    private val repository: PromotionRepository
) {
    fun execute(id: Long, promotion: Promotion): Promotion {
        val promotionUpdated = repository.findById(id) ?: throw PromotionNotFoundException(id)

        promotionUpdated.copy(
            type = promotion.type,
            value = promotion.value,
            startAt = promotion.startAt,
            endAt = promotion.endAt,
            active = promotion.active,
            priority = promotion.priority,
            updatedAt = Instant.now()
        )

        return repository.save(promotionUpdated)
    }
}