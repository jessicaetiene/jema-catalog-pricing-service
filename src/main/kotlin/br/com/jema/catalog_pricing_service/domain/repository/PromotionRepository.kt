package br.com.jema.catalog_pricing_service.domain.repository

import br.com.jema.catalog_pricing_service.domain.Product
import br.com.jema.catalog_pricing_service.domain.Promotion

interface PromotionRepository: Repository<Promotion> {
    fun findActivePromotionsByProduct(product: Product): List<Promotion>
}