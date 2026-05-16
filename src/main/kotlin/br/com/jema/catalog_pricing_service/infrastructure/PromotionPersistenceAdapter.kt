package br.com.jema.catalog_pricing_service.infrastructure

import br.com.jema.catalog_pricing_service.domain.Promotion
import br.com.jema.catalog_pricing_service.domain.repository.PromotionRepository
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toDomain
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toEntity
import br.com.jema.catalog_pricing_service.infrastructure.repository.JpaPromotionRepository


class PromotionPersistenceAdapter(
    val jpaPromotionRepository: JpaPromotionRepository
): PromotionRepository {
    override fun save(domain: Promotion): Promotion {
        return jpaPromotionRepository.save(domain.toEntity()).toDomain()
    }

    override fun findById(id: Long): Promotion? {
        return jpaPromotionRepository.findById(id).map { it.toDomain() }.orElse( null )
    }

    override fun findAll(): List<Promotion> {
        return jpaPromotionRepository.findAll().map { it.toDomain() }
    }
}