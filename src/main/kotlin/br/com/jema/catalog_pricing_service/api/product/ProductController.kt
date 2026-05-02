package br.com.jema.catalog_pricing_service.api.product

import br.com.jema.catalog_pricing_service.application.product.CreateProductUseCase
import br.com.jema.catalog_pricing_service.application.product.DeactivateProductUseCase
import br.com.jema.catalog_pricing_service.application.product.GetProductByIdUseCase
import br.com.jema.catalog_pricing_service.application.product.ListProductsUseCase
import br.com.jema.catalog_pricing_service.application.product.UpdateProductUseCase
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

@RestController
@RequestMapping("/products")
class ProductController (
    private val createProductUseCase: CreateProductUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val listProductsUseCase: ListProductsUseCase,
    private val deactivateProductUseCase: DeactivateProductUseCase
) {

        @PostMapping
    fun create(@RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        val product = createProductUseCase.execute(request)
            return ResponseEntity.status(HttpStatus.CREATED).body(product.toResponse())
    }
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<ProductResponse> {
        val product = getProductByIdUseCase.execute(id)
        return ResponseEntity.ok(product.toResponse())
    }

    @GetMapping
    fun list() : ResponseEntity<List<ProductResponse>> {
        val products = listProductsUseCase.execute()
        return ResponseEntity.ok(products?.map { it.toResponse() })
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: ProductRequest) : ResponseEntity<ProductResponse> {
        val product = updateProductUseCase.execute(id,request)
        return ResponseEntity.ok(product.toResponse())
    }

    @PatchMapping("/{id}/deactivate")
    fun deactivate(@PathVariable id: Long) : ResponseEntity<ProductResponse> {
        val product = deactivateProductUseCase.execute(id)
        return ResponseEntity.ok(product.toResponse())
    }

}