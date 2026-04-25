package br.com.jema.catalog_pricing_service.infrastructure.repository

import br.com.jema.catalog_pricing_service.domain.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaProductRepository : JpaRepository<ProductEntity, Long>