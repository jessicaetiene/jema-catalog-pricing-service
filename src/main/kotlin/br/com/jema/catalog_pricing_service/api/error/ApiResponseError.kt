package br.com.jema.catalog_pricing_service.api.error

import java.time.Instant

class ApiResponseError (
    val timestamp: Instant = Instant.now(),
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
    val details: List<String> = emptyList()
)