package br.com.jema.catalog_pricing_service.application

import br.com.jema.catalog_pricing_service.domain.Product
import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.entity.PromotionType
import java.math.BigDecimal
import java.time.Instant

class DataMockTest {
    companion object {
        val productMock = Product(
            id = 123L,
            name = "product 1",
            description = "product 1",
            price = BigDecimal("100"),
            active = true,
            createdAt = Instant.parse("2026-01-01T00:00:00Z"),
        )

        val promotionMock = Promotion(
            id = 1L,
            product = productMock,
            type = PromotionType.FIXED,
            value = BigDecimal("12"),
            active = true,
            startAt = Instant.parse("2026-01-01T00:00:00Z"),
            endAt = Instant.parse("2026-01-04T00:00:00Z"),
            priority = 1,
            createdAt = Instant.parse("2026-01-01T00:00:00Z")
        )

        val listPromotionMock = listOf(
            Promotion(
                id = 1L,
                product = productMock,
                type = PromotionType.FIXED,
                value = BigDecimal("12"),
                active = true,
                startAt = Instant.parse("2026-01-01T00:00:00Z"),
                endAt = Instant.parse("2026-01-04T00:00:00Z"),
                priority = 1,
                createdAt = Instant.parse("2026-01-01T00:00:00Z")
            ),
            Promotion(
                id = 2L,
                product = productMock,
                type = PromotionType.PERCENTUAL,
                value = BigDecimal("15"),
                active = true,
                startAt = Instant.parse("2026-01-01T00:00:00Z"),
                endAt = Instant.parse("2026-01-04T00:00:00Z"),
                priority = 1,
                createdAt = Instant.parse("2026-01-01T00:00:00Z")
            )
        )
    }
}