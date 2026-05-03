package br.com.jema.catalog_pricing_service.api.product

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ProductRequest (
    @NotBlank(message = "Product name must not be blank")
    val name: String,

    val description: String?,

    @NotNull(message = "Product price must not be null")
    @DecimalMin("0.01", message = "Product price must be greater than zero")
    val price: BigDecimal,

    val active: Boolean = true
)