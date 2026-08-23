package br.com.jema.catalog_pricing_service.application.inventory

import br.com.jema.catalog_pricing_service.domain.Inventory
import br.com.jema.catalog_pricing_service.domain.repository.InventoryRepository
import br.com.jema.catalog_pricing_service.shared.exception.InventoryNotFoundException
import org.springframework.stereotype.Service

@Service
class GetInventoryByProductIdUseCase(
    val repository: InventoryRepository
) {
    fun execute(productId: Long): Inventory {
        return repository.findByProductId(productId) ?: throw InventoryNotFoundException(productId)
    }
}