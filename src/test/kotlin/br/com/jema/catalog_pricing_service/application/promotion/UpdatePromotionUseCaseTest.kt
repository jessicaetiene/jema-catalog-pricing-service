package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.api.promotion.PromotionRequest
import br.com.jema.catalog_pricing_service.application.DataMockTest.Companion.productMock
import br.com.jema.catalog_pricing_service.application.DataMockTest.Companion.promotionMock
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.shared.exception.PromotionNotFoundException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Instant


class UpdatePromotionUseCaseTest {
    private val promotionRepository = mockk<PromotionRepository>()
    private val useCase = UpdatePromotionUseCase(promotionRepository)

    @Test
    fun `Should update promotion successfully`(){
        val promotionUpdated = Promotion(
            id = 123L,
            product = productMock,
            type = PromotionType.FIXED,
            value = BigDecimal("15.00"),
            active = true,
            startAt = Instant.parse("2026-01-01T00:00:00Z"),
            endAt = Instant.parse("2026-01-04T00:00:00Z"),
            priority = 1,
            createdAt = Instant.parse("2026-01-01T00:00:00Z")
        )

        every { promotionRepository.findById(any()) } returns promotionMock
        every { promotionRepository.save(any()) } returns promotionUpdated

        val result = useCase.execute(123L, command)

        assertEquals(command.value, result.value)
    }

    @Test
    fun `Should throw an exception when promotion not found`(){
        every { promotionRepository.findById(any()) } returns null
        val exceptionExpected = assertThrows(PromotionNotFoundException::class.java){
            useCase.execute(123L, command)
        }

        assertEquals("Promotion with id 123 was not found", exceptionExpected.message)
    }

    companion object{
        val command = PromotionRequest(
            productId = 123L,
            type = PromotionType.PERCENTUAL,
            value = BigDecimal("15.00"),
            startAt = Instant.parse("2026-01-01T00:00:00Z"),
            endAt = Instant.parse("2026-01-31T23:59:59Z"),
            active = true,
            priority = 1
        )
    }

}