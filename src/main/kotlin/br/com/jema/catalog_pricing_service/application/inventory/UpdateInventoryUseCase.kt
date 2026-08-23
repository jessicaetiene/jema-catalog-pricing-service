package br.com.jema.catalog_pricing_service.application.inventory

import br.com.jema.catalog_pricing_service.domain.Inventory
import br.com.jema.catalog_pricing_service.domain.repository.InventoryRepository
import org.springframework.stereotype.Service

@Service
class UpdateInventoryUseCase(
    val repository: InventoryRepository
) {
    fun execute(inventoryId: Long, quantity: Int, reservedQuantity: Int): Inventory {
        val inventory = repository.findById(inventoryId) ?: throw IllegalArgumentException("Inventory not found")
        inventory.updateStock(quantity, reservedQuantity)
        return repository.save(inventory)
    }
}