package br.com.jema.catalog_pricing_service.application.pricing

import br.com.jema.catalog_pricing_service.api.price.PriceRequest
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.shared.exception.ProductNotFoundException
import br.com.jema.catalog_pricing_service.shared.exception.PromotionNotFoundException
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CalculateFinalPriceUseCase(
    private val productRepository: ProductRepository,
    private val promotionRepository: PromotionRepository,
    private val pricingCalculator: PricingCalculator
) {
    fun execute(request: PriceRequest): BigDecimal {
        val product = productRepository.findById(request.productId) ?: throw ProductNotFoundException(request.productId)
        val activePromotions = promotionRepository.findActivePromotionsByProduct(product)

        val totalDiscount = activePromotions.map { promotion ->
            pricingCalculator.calculate(product.price, promotion.type, promotion.value)
        }
            .fold(BigDecimal.ZERO, BigDecimal::add)

        return totalDiscount
    }
}