package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import org.springframework.stereotype.Service

@Service
class ListPromotionsUseCase(
    private val repository: PromotionRepository
) {
    fun execute() : List<Promotion>?{
        return repository.findAll()
    }
}