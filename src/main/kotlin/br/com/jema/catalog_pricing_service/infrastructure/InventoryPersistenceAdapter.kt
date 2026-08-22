package br.com.jema.catalog_pricing_service.infrastructure

import br.com.jema.catalog_pricing_service.domain.Inventory
import br.com.jema.catalog_pricing_service.domain.repository.InventoryRepository
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toDomain
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toEntity
import br.com.jema.catalog_pricing_service.infrastructure.repository.JpaInventoryRepository


class InventoryPersistenceAdapter(
    val repository: JpaInventoryRepository
) : InventoryRepository {
    override fun save(domain: Inventory): Inventory {
        return repository.save(domain.toEntity()).toDomain()
    }

    override fun findById(id: Long): Inventory? {
        return repository.findById(id).map { it.toDomain() }.orElse(null)
    }

    override fun findAll(): List<Inventory> {
        return repository.findAll().map { it.toDomain() }
    }
}