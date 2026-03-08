package br.com.jema.catalog_pricing_service.api.health.controller

import br.com.jema.catalog_pricing_service.api.health.response.HealthResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.management.ManagementFactory
import java.time.Instant

@RestController
class HealthController {


    @GetMapping("/health")
    fun health(): ResponseEntity<HealthResponse> {

        val response = HealthResponse(
            status = "UP",
            service = "catalog-pricing-service",
            version = "1.0.0",
            timestamp = Instant.now(),
        )

        return ResponseEntity.ok(response)
    }
}