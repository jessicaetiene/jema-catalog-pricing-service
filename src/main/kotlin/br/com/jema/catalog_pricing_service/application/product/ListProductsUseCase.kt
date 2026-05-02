package br.com.jema.catalog_pricing_service.application.product

import br.com.jema.catalog_pricing_service.domain.Product
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ListProductsUseCase(private val repository: ProductRepository) {
    fun execute(): List<Product>? {
        return repository.findAll()
    }
}