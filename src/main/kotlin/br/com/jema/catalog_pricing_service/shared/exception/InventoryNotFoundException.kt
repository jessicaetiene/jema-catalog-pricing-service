package br.com.jema.catalog_pricing_service.shared.exception

import br.com.jema.catalog_pricing_service.shared.ApplicationException

class InventoryNotFoundException(
    id: Long
) : ApplicationException("Inventory for product with id $id was not found")
