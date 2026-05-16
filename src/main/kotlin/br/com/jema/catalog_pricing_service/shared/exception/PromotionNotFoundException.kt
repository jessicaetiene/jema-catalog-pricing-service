package br.com.jema.catalog_pricing_service.shared.exception

import br.com.jema.catalog_pricing_service.shared.ApplicationException

class PromotionNotFoundException(
    id: Long
) : ApplicationException("Promotion with id $id was not found")