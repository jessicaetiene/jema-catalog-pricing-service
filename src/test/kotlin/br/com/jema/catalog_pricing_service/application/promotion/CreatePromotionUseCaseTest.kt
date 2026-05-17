package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.api.promotion.PromotionRequest
import br.com.jema.catalog_pricing_service.application.DataMockTest.Companion.productMock
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.entity.PromotionEntity
import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toDomain
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toEntity
import br.com.jema.catalog_pricing_service.shared.exception.ProductNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import java.math.BigDecimal
import java.time.Instant

@AutoConfigureMockMvc
class CreatePromotionUseCaseTest {

    private val promotionRepository = mockk<PromotionRepository>()
    private val productRepository = mockk<ProductRepository>()
    private val useCase = CreatePromotionUseCase(promotionRepository, productRepository)


    @Test
    fun `should create promotion successfully`() {
        val command = PromotionRequest(
            productId = 123L,
            type = PromotionType.PERCENTUAL,
            value = BigDecimal("10.00"),
            startAt = Instant.parse("2026-01-01T00:00:00Z"),
            endAt = Instant.parse("2026-01-31T23:59:59Z"),
            active = true,
            priority = 1
        )

        val savedPromotion = PromotionEntity(
            id = 1L,
            product = productMock.toEntity(),
            type = command.type,
            value = command.value,
            startAt = command.startAt,
            endAt = command.endAt,
            active = command.active,
            priority = command.priority
        )

        every { productRepository.findById(any()) } returns productMock
        every { promotionRepository.save(any()) } returns savedPromotion.toDomain()

        val result = useCase.execute(command)

        assertEquals(1L, result.id)
        assertEquals(123L, result.product.id)
        assertEquals(PromotionType.PERCENTUAL, result.type)
        assertEquals(BigDecimal("10.00"), result.value)

        verify(exactly = 1) {
            promotionRepository.save(any())
        }
    }

    @Test
    fun `Should throw exception when product is not foud`(){
        val command = PromotionRequest(
            productId = 123L,
            type = PromotionType.PERCENTUAL,
            value = BigDecimal("10.00"),
            startAt = Instant.parse("2026-01-01T00:00:00Z"),
            endAt = Instant.parse("2026-01-31T23:59:59Z"),
            active = true,
            priority = 1
        )
        every { productRepository.findById(any()) } returns null
        val expectedException = assertThrows(ProductNotFoundException::class.java) {
            useCase.execute(command)
        }

        assertEquals(
            "Product with id 123 was not found",
            expectedException.message
        )
    }
}