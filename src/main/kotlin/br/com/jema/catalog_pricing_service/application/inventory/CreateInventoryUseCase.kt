package br.com.jema.catalog_pricing_service.application.inventory

import br.com.jema.catalog_pricing_service.api.inventory.InventoryRequest
import br.com.jema.catalog_pricing_service.api.inventory.InventoryResponse
import br.com.jema.catalog_pricing_service.domain.Inventory
import br.com.jema.catalog_pricing_service.domain.repository.InventoryRepository
import br.com.jema.catalog_pricing_service.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CreateInventoryUseCase (
    val inventoryRepository: InventoryRepository,
    val productRepository: ProductRepository,
){
    fun execute(inventoryRequest: InventoryRequest): Inventory {
        val product = productRepository.findById(inventoryRequest.productId) ?: throw IllegalArgumentException("Product not found")
        val inventory = Inventory(
            id =  inventoryRequest.id,
            product = product,
            quantity = inventoryRequest.quantity,
            reservedQuantity = inventoryRequest.reservedQuantity
        )
        return inventoryRepository.save(inventory)
    }
}