package br.com.jema.catalog_pricing_service.infrastructure.repository

import br.com.jema.catalog_pricing_service.domain.entity.InventoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaInventoryRepository: JpaRepository<InventoryEntity, Long> {
    fun findByProductId(productId: Long): InventoryEntity?
}