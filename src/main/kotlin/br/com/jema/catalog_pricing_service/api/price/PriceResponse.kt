package br.com.jema.catalog_pricing_service.api.price

import java.math.BigDecimal
import java.time.Instant

data class PriceResponse (
    val price: BigDecimal
)