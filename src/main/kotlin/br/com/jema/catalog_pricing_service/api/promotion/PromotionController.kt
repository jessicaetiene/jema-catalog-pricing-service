package br.com.jema.catalog_pricing_service.api.promotion

import br.com.jema.catalog_pricing_service.api.product.toResponse
import br.com.jema.catalog_pricing_service.application.promotion.CreatePromotionUseCase
import br.com.jema.catalog_pricing_service.application.promotion.DeactivatePromotionUseCase
import br.com.jema.catalog_pricing_service.application.promotion.GetPromotionUseCase
import br.com.jema.catalog_pricing_service.application.promotion.ListPromotionsUseCase
import br.com.jema.catalog_pricing_service.application.promotion.UpdatePromotionUseCase
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/promotions")
class PromotionController(
    private val createPromotionUseCase: CreatePromotionUseCase,
    private val getPromotionUseCase: GetPromotionUseCase,
    private val updatePromotionUseCase: UpdatePromotionUseCase,
    private val listPromotionsUseCase: ListPromotionsUseCase,
    private val deactivatePromotionUseCase: DeactivatePromotionUseCase
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Operation(summary = "Create a new promotion")
    @PostMapping
    fun create(@Valid @RequestBody request: PromotionRequest): ResponseEntity<PromotionResponse> {
        logger.info("Creating promotion for product={}", request.productId)
        val promotion = createPromotionUseCase.execute(request)

        logger.info("Promotion for product={}", request.productId)

        return ResponseEntity
            .created(URI.create("/promotions/${promotion.id}"))
            .body(promotion.toPromotionResponse())
    }

    @Operation(summary = "Get a promotion by id")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<PromotionResponse> {
        logger.info("Find promotion with id={}", id)
        val promotion = getPromotionUseCase.execute(id)
        logger.info("Product with id={} found", id)
        return ResponseEntity.ok(promotion.toPromotionResponse())
    }

    @Operation(summary = "List all promotions")
    @GetMapping
    fun list(): ResponseEntity<List<PromotionResponse>> {
        logger.info("List products")
        val promotionList = listPromotionsUseCase.execute()
        return ResponseEntity.ok(promotionList?.map { it.toPromotionResponse() })
    }

    @Operation(summary = "Update an existing promotion")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: PromotionRequest): ResponseEntity<PromotionResponse> {
        logger.info("Update product with id={}", id)
        val promotion = updatePromotionUseCase.execute(id, request)
        return ResponseEntity.ok(promotion.toPromotionResponse())
    }

    @Operation(summary = "Deactivate a promotion")
    @PatchMapping("/{id}/deactivate")
    fun deactivate(@PathVariable id: Long): ResponseEntity<PromotionResponse> {
        logger.info("Deactivating promotion with id={}", id)
        val promotion = deactivatePromotionUseCase.execute(id)
        return ResponseEntity.ok(promotion.toPromotionResponse())
    }

}