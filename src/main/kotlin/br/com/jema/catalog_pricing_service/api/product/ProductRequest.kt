package br.com.jema.catalog_pricing_service.api.product

import org.springframework.context.annotation.Description
import java.math.BigDecimal

data class ProductRequest (
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val active: Boolean
)