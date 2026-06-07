package br.com.jema.catalog_pricing_service.application.pricing

import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import br.com.jema.catalog_pricing_service.domain.pricing.PricingRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import java.math.BigDecimal

class PricingCalculatorTest {

    private val percentageRule = mockk<PricingRule>()
    private val fixedRule = mockk<PricingRule>()
    private val calculator = PricingCalculator(listOf(percentageRule, fixedRule))

    @Test
    fun `Should calculate price using percentage rule`(){
        every {
            percentageRule.supports(PromotionType.PERCENTUAL)
        } returns true

        every {
            fixedRule.supports(PromotionType.PERCENTUAL)
        } returns false

        every {
            percentageRule.apply(
                BigDecimal("100.00"),
                BigDecimal("10.00")
            )
        } returns BigDecimal("90.00")

        val result = calculator.calculate(
            BigDecimal("100.00"),
            PromotionType.PERCENTUAL,
            BigDecimal("10.00"))

        assertEquals(BigDecimal("90.00"), result)

        verify(exactly = 1) {
            percentageRule.apply(
                BigDecimal("100.00"),
                BigDecimal("10.00")
            )
        }
    }

    @Test
    fun `should calculate price using fixed amount rule`() {

        // Arrange
        every {
            percentageRule.supports(PromotionType.FIXED)
        } returns false

        every {
            fixedRule.supports(PromotionType.FIXED)
        } returns true

        every {
            fixedRule.apply(
                BigDecimal("100.00"),
                BigDecimal("20.00")
            )
        } returns BigDecimal("80.00")

        // Act
        val result = calculator.calculate(
            originalPrice = BigDecimal("100.00"),
            promotionType = PromotionType.FIXED,
            discountPrice = BigDecimal("20.00")
        )

        // Assert
        assertEquals(BigDecimal("80.00"), result)

        verify(exactly = 1) {
            fixedRule.apply(
                BigDecimal("100.00"),
                BigDecimal("20.00")
            )
        }
    }

    @Test
    fun `should throw exception when promotion type is not supported`() {

        // Arrange
        every {
            percentageRule.supports(PromotionType.PERCENTUAL)
        } returns false

        every {
            fixedRule.supports(PromotionType.PERCENTUAL)
        } returns false

        // Act + Assert
        val exception = assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate(
                originalPrice = BigDecimal("100.00"),
                promotionType = PromotionType.PERCENTUAL,
                discountPrice = BigDecimal("10.00")
            )
        }

        assertEquals(
            "Unsupported promotion type: PERCENTUAL",
            exception.message
        )
    }

}