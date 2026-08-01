package br.com.jema.catalog_pricing_service.domain

import java.time.Instant

class Inventory(

    val id: Long? = null,

    val product: Product,

    quantity: Int,

    reservedQuantity: Int,

    val createdAt: Instant = Instant.now(),

    updatedAt: Instant? = null

) {

    var quantity: Int = quantity
        private set

    var reservedQuantity: Int = reservedQuantity
        private set

    var updatedAt: Instant? = updatedAt
        private set

    init {
        require(quantity >= 0) {
            "Quantity cannot be negative."
        }

        require(reservedQuantity >= 0) {
            "Reserved quantity cannot be negative."
        }

        require(reservedQuantity <= quantity) {
            "Reserved quantity cannot exceed available quantity."
        }
    }

    val availableQuantity: Int
        get() = quantity - reservedQuantity

    val available: Boolean
        get() = availableQuantity > 0

    fun increaseStock(amount: Int) {
        validatePositiveAmount(amount)

        quantity += amount
        touch()
    }

    fun decreaseStock(amount: Int) {
        validatePositiveAmount(amount)

        require(availableQuantity >= amount) {
            "Insufficient stock."
        }

        quantity -= amount
        touch()
    }

    fun reserve(amount: Int) {
        validatePositiveAmount(amount)

        require(availableQuantity >= amount) {
            "Insufficient stock."
        }

        reservedQuantity += amount
        touch()
    }

    fun release(amount: Int) {
        validatePositiveAmount(amount)

        require(reservedQuantity >= amount) {
            "Amount exceeds reserved quantity."
        }

        reservedQuantity -= amount
        touch()
    }

    private fun validatePositiveAmount(amount: Int) {
        require(amount > 0) {
            "Amount must be greater than zero."
        }
    }

    private fun touch() {
        updatedAt = Instant.now()
    }
}