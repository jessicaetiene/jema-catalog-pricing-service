package br.com.jema.catalog_pricing_service.api.price

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PriceRequest (
    @NotBlank(message = "Product ID")
    val productId: Long
)