package br.com.jema.catalog_pricing_service.infrastructure.mapper

import br.com.jema.catalog_pricing_service.domain.Inventory
import br.com.jema.catalog_pricing_service.domain.entity.InventoryEntity

fun Inventory.toEntity(): InventoryEntity = InventoryEntity(
    id = id,
    product = product.toEntity(),
    quantity = quantity,
    reservedQuantity = reservedQuantity,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun InventoryEntity.toDomain(): Inventory = Inventory(
    id = id,
    product = product.toDomain(),
    quantity = quantity,
    reservedQuantity = reservedQuantity,
    createdAt = createdAt,
    updatedAt = updatedAt
)