package br.com.jema.catalog_pricing_service.application.inventory

import br.com.jema.catalog_pricing_service.domain.repository.InventoryRepository
import br.com.jema.catalog_pricing_service.shared.exception.InventoryNotFoundException
import org.springframework.stereotype.Service

@Service
class DecreaseStockUseCase(
    val repository: InventoryRepository
) {
    fun execute(inventoryId: Long, quantity: Int){
        val inventory = repository.findById(inventoryId) ?: throw InventoryNotFoundException(inventoryId)
        inventory.decreaseStock(quantity)
        repository.save(inventory)
    }
}