package br.com.jema.catalog_pricing_service.application.product

import br.com.jema.catalog_pricing_service.domain.Product
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import br.com.jema.catalog_pricing_service.shared.exception.ProductNotFoundException
import org.springframework.stereotype.Service

@Service
class GetProductByIdUseCase(private val repository: ProductRepository) {
    fun execute(id: Long): Product {
        return repository.findById(id) ?: throw ProductNotFoundException(id)
    }
}