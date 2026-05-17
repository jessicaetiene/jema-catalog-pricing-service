package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.application.DataMockTest.Companion.productMock
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.entity.PromotionEntity
import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toDomain
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import java.math.BigDecimal
import java.time.Instant

@AutoConfigureMockMvc
class DeactivatePromotionUseCaseTest {

    private val promotionRepository = mockk<PromotionRepository>()
    private val useCase = DeactivatePromotionUseCase(promotionRepository)

    @Test
    fun `should deactivate promotion successfully`() {
        val id = 1L
        val promotion = Promotion(
            product = productMock,
            type = PromotionType.PERCENTUAL,
            value = BigDecimal("10.00"),
            startAt = Instant.parse("2026-01-01T00:00:00Z"),
            endAt = Instant.parse("2026-01-31T23:59:59Z"),
            active = true,
            priority = 1
        )

        val deactivatedPromotion = promotion.copy(
            active = false
        )

        every { promotionRepository.findById(any()) } returns promotion
        every { promotionRepository.save(any()) } returns deactivatedPromotion

        val result = useCase.execute(id)

        assertFalse(result.active)

        verify(exactly = 1) {
            promotionRepository.save(any())
        }
    }
}