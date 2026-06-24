package br.com.jema.catalog_pricing_service.api.price

import br.com.jema.catalog_pricing_service.api.product.ProductRequest
import br.com.jema.catalog_pricing_service.api.product.ProductResponse
import br.com.jema.catalog_pricing_service.api.product.toResponse
import br.com.jema.catalog_pricing_service.application.pricing.CalculateFinalPriceUseCase
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
    name = "Price",
    description = "Endpoints for calculate final"
)
@RestController
@RequestMapping("/price")
class PriceController(
    private val calculateFinalPriceUseCase: CalculateFinalPriceUseCase,
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Operation(summary = "Calculate final product price")
    @PostMapping
    fun create(@Valid @RequestBody request: PriceRequest): ResponseEntity<PriceResponse> {
        logger.info("Calculate price for product={}", request.productId)
        val finalPrice = calculateFinalPriceUseCase.execute(request)

//        logger.info("Product name={} created", request.name)

        return ResponseEntity.ok(PriceResponse(finalPrice))

    }


}