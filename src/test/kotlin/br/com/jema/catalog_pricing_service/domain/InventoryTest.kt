package br.com.jema.catalog_pricing_service.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class InventoryTest {

    @Test
    fun `should create inventory successfully`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 2
        )

        assertEquals(10, inventory.quantity)
        assertEquals(2, inventory.reservedQuantity)
        assertEquals(8, inventory.availableQuantity)
        assertTrue(inventory.available)
    }

    @Test
    fun `should throw exception when quantity is negative`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Inventory(
                product = createProduct(),
                quantity = -1,
                reservedQuantity = 0
            )
        }

        assertEquals(
            "Quantity cannot be negative.",
            exception.message
        )
    }

    @Test
    fun `should throw exception when reserved quantity is negative`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Inventory(
                product = createProduct(),
                quantity = 10,
                reservedQuantity = -1
            )
        }

        assertEquals(
            "Reserved quantity cannot be negative.",
            exception.message
        )
    }

    @Test
    fun `should throw exception when reserved quantity is greater than quantity`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Inventory(
                product = createProduct(),
                quantity = 5,
                reservedQuantity = 6
            )
        }

        assertEquals(
            "Reserved quantity cannot exceed available quantity.",
            exception.message
        )
    }

    @Test
    fun `should calculate available quantity`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 15,
            reservedQuantity = 4
        )

        assertEquals(11, inventory.availableQuantity)
    }

    @Test
    fun `should return true when inventory is available`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 9
        )

        assertTrue(inventory.available)
        assertEquals(1, inventory.availableQuantity)
    }

    @Test
    fun `should return false when inventory is unavailable`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 10
        )

        assertFalse(inventory.available)
        assertEquals(0, inventory.availableQuantity)
    }

    @Test
    fun `should increase stock`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 2
        )

        inventory.increaseStock(5)

        assertEquals(15, inventory.quantity)
        assertEquals(13, inventory.availableQuantity)
        assertNotNull(inventory.updatedAt)
    }

    @Test
    fun `should reject invalid amount when increasing stock`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 0
        )

        assertThrows(IllegalArgumentException::class.java) {
            inventory.increaseStock(0)
        }
    }

    @Test
    fun `should decrease stock`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 2
        )

        inventory.decreaseStock(3)

        assertEquals(7, inventory.quantity)
        assertEquals(2, inventory.reservedQuantity)
        assertEquals(5, inventory.availableQuantity)
        assertNotNull(inventory.updatedAt)
    }

    @Test
    fun `should not decrease stock when available quantity is insufficient`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 8
        )

        val exception = assertThrows(IllegalArgumentException::class.java) {
            inventory.decreaseStock(3)
        }

        assertEquals("Insufficient stock.", exception.message)
        assertEquals(10, inventory.quantity)
    }

    @Test
    fun `should reserve stock`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 2
        )

        inventory.reserve(3)

        assertEquals(5, inventory.reservedQuantity)
        assertEquals(5, inventory.availableQuantity)
        assertNotNull(inventory.updatedAt)
    }

    @Test
    fun `should not reserve more than available quantity`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 7
        )

        val exception = assertThrows(IllegalArgumentException::class.java) {
            inventory.reserve(4)
        }

        assertEquals("Insufficient stock.", exception.message)
        assertEquals(7, inventory.reservedQuantity)
    }

    @Test
    fun `should release reserved stock`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 6
        )

        inventory.release(2)

        assertEquals(4, inventory.reservedQuantity)
        assertEquals(6, inventory.availableQuantity)
        assertNotNull(inventory.updatedAt)
    }

    @Test
    fun `should not release more than reserved quantity`() {
        val inventory = Inventory(
            product = createProduct(),
            quantity = 10,
            reservedQuantity = 3
        )

        assertThrows(IllegalArgumentException::class.java) {
            inventory.release(4)
        }

        assertEquals(3, inventory.reservedQuantity)
    }

    private fun createProduct(): Product {
        return Product(
            id = 1L,
            name = "Diving Computer",
            description = "Computer for recreational diving",
            price = BigDecimal("499.99"),
            active = true
        )
    }
}