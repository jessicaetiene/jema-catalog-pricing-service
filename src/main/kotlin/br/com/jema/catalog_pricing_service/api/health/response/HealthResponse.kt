package br.com.jema.catalog_pricing_service.api.health.response

import java.time.Instant

data class HealthResponse(
    val status: String,
    val service: String,
    val version: String,
    val timestamp: Instant
)