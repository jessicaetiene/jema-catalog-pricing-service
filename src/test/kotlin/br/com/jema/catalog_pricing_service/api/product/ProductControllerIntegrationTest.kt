package br.com.jema.catalog_pricing_service.api.product

import br.com.jema.catalog_pricing_service.domain.entity.ProductEntity
import br.com.jema.catalog_pricing_service.infrastructure.repository.JpaProductRepository
import br.com.jema.catalog_pricing_service.support.IntegrationTest
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import java.math.BigDecimal
import java.time.Instant
import kotlin.math.exp

@AutoConfigureMockMvc
class ProductControllerIntegrationTest : IntegrationTest() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var productJpaRepository: JpaProductRepository

    @BeforeEach
    fun cleanDatabase() {
        productJpaRepository.deleteAll()
    }

    @Test
    fun `Should create a product and return status code 200`(){
        val request = """
            {
              "name": "Diving Computer",
              "description": "Dive computer for recreational scuba diving",
              "price": 499.99,
              "active": true
            }
        """.trimIndent()

        mockMvc.post("/products") {
            contentType = MediaType.APPLICATION_JSON
            content = request
        }.andExpect {
            status { isCreated() }
            jsonPath("$.name") { value("Diving Computer") }
        }
    }

    @Test
    fun `Should get a product by id and return status code 200`(){

        val savedProduct = productJpaRepository.save(
            ProductEntity(
                name = "Diving Computer",
                description = "Scuba diving device",
                price = BigDecimal("499.99"),
                active = true,
                createdAt = Instant.now(),
                updatedAt = null
            )
        )

        val id = savedProduct.id

        mockMvc.get("/products/${id}") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.id") {value(id)}
            jsonPath("$.name") {value("Diving Computer")}
            jsonPath("$.description") {value("Scuba diving device")}
            jsonPath("$.price") {value("499.99")}
            jsonPath("$.active") {value(true)}
            jsonPath("$.createdAt") {value(savedProduct.createdAt.toString())}
        }
    }

    @Test
    fun `Should return status code 404 when productId is not found`(){
        val id = 666
        mockMvc.get("/products/${id}") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNotFound() }
            jsonPath("$.message") {value("Product with id ${id} was not found")}
            jsonPath("$.error") {value("Not Found")}
            jsonPath("$.status") {value("404")}
            jsonPath("$.path") {value("/products/${id}")}
        }
    }

    @Test
    fun `Should get list of products and return status code 200`(){
        val savedProduct1 = productJpaRepository.save(
            ProductEntity(
                name = "Diving Computer",
                description = "Scuba diving device",
                price = BigDecimal("499.99"),
                active = true,
                createdAt = Instant.now(),
                updatedAt = null
            )
        )

        val savedProduct2 = productJpaRepository.save(
            ProductEntity(
                name = "Cable USB-C",
                description = "Cable USB Type C - Black",
                price = BigDecimal("10.99"),
                active = true,
                createdAt = Instant.now(),
                updatedAt = null
            )
        )
        mockMvc.get("/products") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$") {hasSize<Int>(2)}
        }
    }

    @Test
    fun `Should update a product and return status code 200`(){
        val savedProduct = productJpaRepository.save(
            ProductEntity(
                name = "Diving Computer",
                description = "Scuba diving device",
                price = BigDecimal("499.99"),
                active = true,
                createdAt = Instant.now(),
                updatedAt = null
            )
        )

        val request = """
            {
              "id": "${savedProduct.id}",
              "name": "Diving Computer 2.0",
              "description": "Scuba diving device 2.0",
              "price": 599.99,
              "active": true
            }
        """.trimIndent()

        mockMvc.put("/products/${savedProduct.id}") {
            contentType = MediaType.APPLICATION_JSON
            content = request
        }.andExpect {
            status { isOk() }
            jsonPath("$.id") {value(savedProduct.id)}
            jsonPath("$.name") {value("Diving Computer 2.0")}
            jsonPath("$.description") {value("Scuba diving device 2.0")}
            jsonPath("$.price") {value("599.99")}
            jsonPath("$.active") {value(true)}
            jsonPath("$.createdAt") {value(savedProduct.createdAt.toString())}
            jsonPath("$.updateAt") { exists() }
        }
    }

    @Test
    fun `Should deactivate a product and return status code 200`(){
        val savedProduct = productJpaRepository.save(
            ProductEntity(
                name = "Diving Computer",
                description = "Scuba diving device",
                price = BigDecimal("499.99"),
                active = true,
                createdAt = Instant.now(),
                updatedAt = null
            )
        )

        mockMvc.patch("/products/${savedProduct.id}/deactivate") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            jsonPath("$.id") {value(savedProduct.id)}
            jsonPath("$.name") {value(savedProduct.name)}
            jsonPath("$.description") {value(savedProduct.description)}
            jsonPath("$.price") {value(savedProduct.price)}
            jsonPath("$.active") {value(false)}
            jsonPath("$.createdAt") {value(savedProduct.createdAt.toString())}
        }
    }
}