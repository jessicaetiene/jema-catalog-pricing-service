package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.application.DataMockTest.Companion.productMock
import br.com.jema.catalog_pricing_service.domain.Product
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.entity.PromotionEntity
import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toDomain
import br.com.jema.catalog_pricing_service.shared.exception.PromotionNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import java.math.BigDecimal
import java.time.Instant

@AutoConfigureMockMvc
class GetPromotionUseCaseTest {

    private val promotionRepository = mockk<PromotionRepository>()
    private val useCase = GetPromotionUseCase(promotionRepository)


    @Test
    fun `should get promotion by id successfully`() {
        val id = 1L

        val promotion = Promotion(
            id = 1L,
            product = productMock,
            type = PromotionType.PERCENTUAL,
            value = BigDecimal("10.00"),
            startAt = Instant.parse("2026-01-01T00:00:00Z"),
            endAt = Instant.parse("2026-01-31T23:59:59Z"),
            active = true,
            priority = 1
        )

        every { promotionRepository.findById(any()) } returns promotion

        val result = useCase.execute(id)

        assertEquals(1L, result.id)
        assertEquals(123L, result.product.id)
        assertEquals(PromotionType.PERCENTUAL, result.type)
        assertEquals(BigDecimal("10.00"), result.value)
    }

    @Test
    fun `should throw exception when promotion is not found`() {
        every { promotionRepository.findById(999L) } returns null
        val exception = assertThrows(PromotionNotFoundException::class.java) {
            useCase.execute(999L)
        }

        assertEquals(
            "Promotion with id 999 was not found",
            exception.message
        )
    }
}