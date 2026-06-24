package br.com.jema.catalog_pricing_service.domain.pricing

import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import org.springframework.stereotype.Component
import java.math.BigDecimal
@Component
class PercentageDiscountRule : PricingRule {
    override fun supports(type: PromotionType): Boolean = type == PromotionType.PERCENTUAL

    override fun apply(originalPrice: BigDecimal, discountValue: BigDecimal): BigDecimal {
        val discount = originalPrice.multiply(discountValue).divide(BigDecimal("100"))
        return originalPrice.subtract(discount).max(BigDecimal.ZERO)
    }
}