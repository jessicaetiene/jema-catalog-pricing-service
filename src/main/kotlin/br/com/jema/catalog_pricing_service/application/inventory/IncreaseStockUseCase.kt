package br.com.jema.catalog_pricing_service.application.inventory

import br.com.jema.catalog_pricing_service.domain.Inventory
import br.com.jema.catalog_pricing_service.domain.repository.InventoryRepository
import br.com.jema.catalog_pricing_service.shared.exception.InventoryNotFoundException
import org.springframework.stereotype.Service

@Service
class IncreaseStockUseCase(
    val repository: InventoryRepository
) {
    fun execute(inventoryId: Long, quantity: Int): Inventory{
        val inventory = repository.findById(inventoryId) ?: throw InventoryNotFoundException(inventoryId)
        inventory.increaseStock(quantity)
        return repository.save(inventory)
    }
}