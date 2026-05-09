package br.com.jema.catalog_pricing_service.api.product

import br.com.jema.catalog_pricing_service.application.product.CreateProductUseCase
import br.com.jema.catalog_pricing_service.application.product.DeactivateProductUseCase
import br.com.jema.catalog_pricing_service.application.product.GetProductByIdUseCase
import br.com.jema.catalog_pricing_service.application.product.ListProductsUseCase
import br.com.jema.catalog_pricing_service.application.product.UpdateProductUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
@Tag(
    name = "Products",
    description = "Endpoints for product catalog management"
)
@RestController
@RequestMapping("/products")
class ProductController(
    private val createProductUseCase: CreateProductUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val listProductsUseCase: ListProductsUseCase,
    private val deactivateProductUseCase: DeactivateProductUseCase
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Operation(summary = "Create a new product")
    @PostMapping
    fun create(@Valid @RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        logger.info("Creating product with name={}", request.name)
        val product = createProductUseCase.execute(request)

        logger.info("Product name={} created", request.name)

        return ResponseEntity
            .created(URI.create("/products/${product.id}"))
            .body(product.toResponse())
    }

    @Operation(summary = "Get a product by id")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        logger.info("Find product with id={}", id)
        val product = getProductByIdUseCase.execute(id)
        logger.info("Product with id={} found", id)
        return ResponseEntity.ok(product.toResponse())
    }

    @Operation(summary = "List all products")
    @GetMapping
    fun list(): ResponseEntity<List<ProductResponse>> {
        logger.info("List products")
        val products = listProductsUseCase.execute()
        return ResponseEntity.ok(products?.map { it.toResponse() })
    }

    @Operation(summary = "Update an existing product")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        logger.info("Update product with id={}", id)
        val product = updateProductUseCase.execute(id, request)
        return ResponseEntity.ok(product.toResponse())
    }

    @Operation(summary = "Deactivate a product")
    @PatchMapping("/{id}/deactivate")
    fun deactivate(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        logger.info("Deactivating product with id={}", id)
        val product = deactivateProductUseCase.execute(id)
        return ResponseEntity.ok(product.toResponse())
    }

}