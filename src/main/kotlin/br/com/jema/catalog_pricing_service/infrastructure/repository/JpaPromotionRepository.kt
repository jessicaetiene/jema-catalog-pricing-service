package br.com.jema.catalog_pricing_service.infrastructure.repository

import br.com.jema.catalog_pricing_service.domain.entity.PromotionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaPromotionRepository: JpaRepository<PromotionEntity, Long> {
}