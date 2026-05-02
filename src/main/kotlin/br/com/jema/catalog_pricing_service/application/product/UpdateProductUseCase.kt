package br.com.jema.catalog_pricing_service.application.product

import br.com.jema.catalog_pricing_service.api.product.ProductRequest
import br.com.jema.catalog_pricing_service.domain.Product
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import br.com.jema.catalog_pricing_service.shared.exception.ProductNotFoundException
import org.springframework.stereotype.Service

@Service
class UpdateProductUseCase(private val repository: ProductRepository) {
    fun execute(id: Long, productRequest: ProductRequest) : Product {
        val product = repository.findById(id) ?: throw ProductNotFoundException(id)

        val updateProduct = product.copy(
            name = productRequest.name,
            description = productRequest.description,
            price = productRequest.price,
            active = productRequest.active
        )
        return repository.save(updateProduct)
    }
}