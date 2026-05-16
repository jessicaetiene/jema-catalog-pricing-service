package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.shared.exception.PromotionNotFoundException

class DeactivatePromotionUseCase(
    private val repository: PromotionRepository
) {
    fun execute(id: Long): Promotion {
        val promotion = repository.findById(id) ?: throw PromotionNotFoundException(id)
        return repository.save(promotion.deactivate())
    }
}