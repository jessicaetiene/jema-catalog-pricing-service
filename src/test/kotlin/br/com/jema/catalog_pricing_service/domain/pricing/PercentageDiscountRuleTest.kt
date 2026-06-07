package br.com.jema.catalog_pricing_service.domain.pricing

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal

class PercentageDiscountRuleTest {

    private val percentageDiscountRule = PercentageDiscountRule()

    @Test
    fun `Should return final value after fixed amount discount`(){
        val originalPricing = BigDecimal("150")
        val discountValue = BigDecimal("15")
        val expected = BigDecimal("127.5")

        val result = percentageDiscountRule.apply(originalPricing, discountValue)
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["100", "101"])
    fun `Should return zero when discount is equal or greater than original value `(value: String){
        val originalPricing = BigDecimal(value)
        val discountValue = BigDecimal(value)
        val expected = BigDecimal.ZERO

        val result = percentageDiscountRule.apply(originalPricing, discountValue)
        assertEquals(expected, result)
    }

}