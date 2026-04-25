package br.com.jema.catalog_pricing_service.domain

import java.math.BigDecimal
import java.time.Instant

data class Product(
    val id: Long?,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val active: Boolean,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant?
) {
    init {
        require(name.isNotBlank()) {
            "Product name must not be blank"
        }

        require(price > BigDecimal.ZERO) {
            "Product base price must be greater than zero"
        }

        /**
         * Business behavior: deactivate a product
         */
        fun deactivate(): Product {
            return copy(
                active = false,
                updatedAt = Instant.now()
            )
        }

        /**
         * Business behavior: update product price
         */
        fun updatePrice(newPrice: BigDecimal): Product {
            require(newPrice > BigDecimal.ZERO) {
                "New price must be greater than zero"
            }

            return copy(
                price = newPrice,
                updatedAt = Instant.now()
            )
        }
    }
}