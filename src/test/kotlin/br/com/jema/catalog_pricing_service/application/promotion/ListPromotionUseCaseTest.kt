package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.application.DataMockTest.Companion.listPromotionMock
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ListPromotionUseCaseTest {
    private val promotionRepository =  mockk<PromotionRepository>()
    private val useCase = ListPromotionsUseCase(promotionRepository)

    @Test
    fun `Should return list of promotions successfully`(){
        every { promotionRepository.findAll() } returns listPromotionMock
        val result = useCase.execute()
        assertEquals(2, result?.size)
    }

    @Test
    fun `Should return empty list of promotions`(){
        every { promotionRepository.findAll() } returns emptyList()
        val result = useCase.execute()
        assertEquals(0, result?.size)
    }

}