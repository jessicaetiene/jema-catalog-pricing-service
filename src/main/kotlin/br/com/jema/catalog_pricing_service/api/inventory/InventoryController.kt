package br.com.jema.catalog_pricing_service.api.inventory

import br.com.jema.catalog_pricing_service.application.inventory.CreateInventoryUseCase
import br.com.jema.catalog_pricing_service.application.inventory.DecreaseStockUseCase
import br.com.jema.catalog_pricing_service.application.inventory.GetInventoryByProductIdUseCase
import br.com.jema.catalog_pricing_service.application.inventory.IncreaseStockUseCase
import br.com.jema.catalog_pricing_service.application.inventory.UpdateInventoryUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(
    name = "Inventory",
    description = "Endpoints for inventory management"
)
@RestController
@RequestMapping("/inventory")
class InventoryController(
    val createInventoryUseCase: CreateInventoryUseCase,
    val getInventoryByProductIdUseCase: GetInventoryByProductIdUseCase,
    val updateInventoryUseCase: UpdateInventoryUseCase,
    val increaseStockUseCase: IncreaseStockUseCase,
    val decreaseStockUseCase: DecreaseStockUseCase
) {

    @PostMapping
    @Operation(summary = "Create inventory")
    fun createInventory(request: InventoryRequest): InventoryResponse {
        val inventory = createInventoryUseCase.execute(request)
        return InventoryResponse(inventory.id!!, inventory.product.id, inventory.quantity, inventory.reservedQuantity)
    }

    @GetMapping
    @Operation(summary = "Get inventory by product ID")
    fun getInventory(productId: Long): InventoryResponse {
        val inventory = getInventoryByProductIdUseCase.execute(productId)
        return InventoryResponse(inventory.id!!, inventory.product.id, inventory.quantity, inventory.reservedQuantity)
    }

    @PutMapping
    @Operation(summary = "Update inventory")
    fun updateInventory(request: InventoryRequest): InventoryResponse {
        val inventory = updateInventoryUseCase.execute(request)
        return InventoryResponse(inventory.id!!, inventory.product.id, inventory.quantity, inventory.reservedQuantity)
    }

    @PostMapping("/increase")
    @Operation(summary = "Increase stock")
    fun increaseStock(request: InventoryRequest): InventoryResponse {
        val inventory = increaseStockUseCase.execute(request.id, request.quantity)
        return InventoryResponse(inventory.id!!, inventory.product.id, inventory.quantity, inventory.reservedQuantity)
    }

    @PostMapping("/decrease")
    @Operation(summary = "Decrease stock")
    fun decreaseStock(request: InventoryRequest): InventoryResponse {
        val inventory = decreaseStockUseCase.execute(request.id, request.quantity)
        return InventoryResponse(inventory.id!!, inventory.product.id, inventory.quantity, inventory.reservedQuantity)
    }
}