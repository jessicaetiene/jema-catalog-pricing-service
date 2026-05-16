package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.api.product.ProductRequest
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository

class CreatePromotionUseCase(
    private val repository: PromotionRepository
) {
    fun execute(promotion: Promotion): Promotion {
        return repository.save(promotion)
    }
}