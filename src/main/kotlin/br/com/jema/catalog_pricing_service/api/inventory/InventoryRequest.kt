package br.com.jema.catalog_pricing_service.api.inventory

data class InventoryRequest(
    val id: Long,
    val productId: Long,
    val quantity: Int,
    val reservedQuantity: Int
)