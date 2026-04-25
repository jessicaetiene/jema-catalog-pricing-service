package br.com.jema.catalog_pricing_service.infrastructure

import br.com.jema.catalog_pricing_service.domain.Product
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toDomain
import br.com.jema.catalog_pricing_service.infrastructure.mapper.toEntity
import br.com.jema.catalog_pricing_service.infrastructure.repository.JpaProductRepository
import org.springframework.stereotype.Component

@Component
class ProductPersistenceAdapter (
    private val repository: JpaProductRepository
): ProductRepository {
    override fun save(domain: Product): Product {
        return repository.save(domain.toEntity()).toDomain()
    }

    override fun findById(id: Long): Product? {
        return repository.findById(id).map { it.toDomain() }.orElse( null )
    }

    override fun findAll(): List<Product> {
        return repository.findAll().map { it.toDomain() }
    }

}