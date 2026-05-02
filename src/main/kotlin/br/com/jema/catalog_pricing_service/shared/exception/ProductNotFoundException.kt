package br.com.jema.catalog_pricing_service.shared.exception

import br.com.jema.catalog_pricing_service.shared.ApplicationException

class ProductNotFoundException(
    id: Long
) : ApplicationException("Product with id $id was not found")