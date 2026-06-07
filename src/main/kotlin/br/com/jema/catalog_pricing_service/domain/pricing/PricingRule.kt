package br.com.jema.catalog_pricing_service.domain.pricing

import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import java.math.BigDecimal

interface PricingRule {
    fun supports(type: PromotionType): Boolean
    fun apply(originalPrice: BigDecimal, discountValue: BigDecimal): BigDecimal
}