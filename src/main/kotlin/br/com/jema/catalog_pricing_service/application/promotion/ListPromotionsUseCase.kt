package br.com.jema.catalog_pricing_service.application.promotion

import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository

class ListPromotionsUseCase(
    private val repository: PromotionRepository
) {
    fun execute() : List<Promotion>?{
        return repository.findAll()
    }
}