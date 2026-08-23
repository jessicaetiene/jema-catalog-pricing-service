package br.com.jema.catalog_pricing_service.application.inventory

import br.com.jema.catalog_pricing_service.api.inventory.InventoryRequest
import br.com.jema.catalog_pricing_service.domain.Inventory
import br.com.jema.catalog_pricing_service.domain.repository.InventoryRepository
import org.springframework.stereotype.Service

@Service
class UpdateInventoryUseCase(
    val repository: InventoryRepository
) {
    fun execute(inventoryRequest: InventoryRequest): Inventory {
        val inventory = repository.findById(inventoryRequest.id) ?: throw IllegalArgumentException("Inventory not found")
        inventory.updateStock(inventoryRequest.quantity, inventoryRequest.reservedQuantity)
        return repository.save(inventory)
    }
}