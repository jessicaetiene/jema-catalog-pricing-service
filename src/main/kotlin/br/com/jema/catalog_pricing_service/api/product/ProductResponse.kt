package br.com.jema.catalog_pricing_service.api.product

import java.math.BigDecimal
import java.time.Instant

data class ProductResponse (
    val id: Long,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val active: Boolean,
    val createdAt: Instant,
    val updateAt: Instant
)