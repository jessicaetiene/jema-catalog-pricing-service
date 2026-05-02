package br.com.jema.catalog_pricing_service.application.product

import br.com.jema.catalog_pricing_service.api.product.ProductRequest
import br.com.jema.catalog_pricing_service.domain.Product
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CreateProductUseCase(
    private val repository: ProductRepository
) {
    fun execute(productRequest: ProductRequest): Product {
        val product = Product(
            name = productRequest.name,
            description = productRequest.description,
            price = productRequest.price,
            active = productRequest.active
        )
        return repository.save(product)
    }
}