package br.com.jema.catalog_pricing_service.application.pricing

import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import br.com.jema.catalog_pricing_service.domain.pricing.PricingRule
import org.springframework.stereotype.Component
import java.math.BigDecimal
@Component
class PricingCalculator(
    private val rules: List<PricingRule>
) {
    fun calculate(
        originalPrice: BigDecimal,
        promotionType: PromotionType,
        discountPrice: BigDecimal
    ): BigDecimal {
        val rule = rules.firstOrNull { it.supports(promotionType) }
            ?: throw IllegalArgumentException("Unsupported promotion type: $promotionType")

        return rule.apply(originalPrice, discountPrice)
    }
}