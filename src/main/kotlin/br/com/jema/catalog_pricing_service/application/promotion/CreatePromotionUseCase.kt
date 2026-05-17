package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.api.product.ProductRequest
import br.com.jema.catalog_pricing_service.api.promotion.PromotionRequest
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.shared.exception.ProductNotFoundException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class CreatePromotionUseCase(
    private val repository: PromotionRepository,
    private val productRepository: ProductRepository
) {
    fun execute(promotionRequest: PromotionRequest): Promotion {
        val product = productRepository.findById(promotionRequest.productId) ?: throw ProductNotFoundException(promotionRequest.productId)
        val promotion = Promotion(
                product = product,
                type = promotionRequest.type,
                value = promotionRequest.value,
                startAt = promotionRequest.startAt,
                endAt = promotionRequest.endAt,
                active = promotionRequest.active,
                priority = promotionRequest.priority,
        )
        return repository.save(promotion)
    }
}