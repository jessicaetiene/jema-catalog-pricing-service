package br.com.jema.catalog_pricing_service.domain.pricing

import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import java.math.BigDecimal

class FixedAmountDiscountRule: PricingRule {
    override fun supports(type: PromotionType): Boolean = type == PromotionType.FIXED

    override fun apply(originalPrice: BigDecimal, discountValue: BigDecimal): BigDecimal {
        return originalPrice.subtract(discountValue).max(BigDecimal.ZERO)
    }
}