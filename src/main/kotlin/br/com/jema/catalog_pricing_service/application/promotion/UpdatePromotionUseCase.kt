package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.api.promotion.PromotionRequest
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.shared.exception.PromotionNotFoundException
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant
import kotlin.Long
@Service
class UpdatePromotionUseCase(
    private val repository: PromotionRepository
) {
    fun execute(id: Long, promotionRequest: PromotionRequest): Promotion {
        val promotion = repository.findById(id) ?: throw PromotionNotFoundException(id)

        val promotionUpdated = promotion.copy(
            type = promotionRequest.type,
            value = promotionRequest.value,
            startAt = promotionRequest.startAt,
            endAt = promotionRequest.endAt,
            active = promotionRequest.active,
            priority = promotionRequest.priority,
            updatedAt = Instant.now()
        )

        return repository.save(promotionUpdated)
    }
}