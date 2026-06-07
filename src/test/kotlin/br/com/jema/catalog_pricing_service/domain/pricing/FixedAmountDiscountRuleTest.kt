package br.com.jema.catalog_pricing_service.domain.pricing

import br.com.jema.catalog_pricing_service.application.promotion.DeactivatePromotionUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal

class FixedAmountDiscountRuleTest {

    private val fixedAmountDiscountRule = FixedAmountDiscountRule()

    @Test
    fun `Should return final value after fixed amount discount`(){
        val originalPricing = BigDecimal("150")
        val discountValue = BigDecimal("12")
        val expected = BigDecimal("138")

        val result = fixedAmountDiscountRule.apply(originalPricing, discountValue)
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["150", "150"])
    fun `Should return zero when discount is equal or greater than original value `(value: String){
        val originalPricing = BigDecimal(value)
        val discountValue = BigDecimal(value)
        val expected = BigDecimal.ZERO

        val result = fixedAmountDiscountRule.apply(originalPricing, discountValue)
        assertEquals(expected, result)
    }

}