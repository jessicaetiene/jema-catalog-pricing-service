package br.com.jema.catalog_pricing_service.domain.repository

/**
 * Marker interface for domain repositories.
 */

interface Repository<T> {
    fun save(domain: T): T

    fun findById(id: Long): T?

    fun findAll(): List<T>
}