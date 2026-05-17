package br.com.jema.catalog_pricing_service.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.Instant

@Entity
@Table(name = "tbl_promotions")
class PromotionEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid", nullable = false)
    val product: ProductEntity,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: PromotionType,

    @Column(name = "value", nullable = false, precision = 10, scale = 2)
    val value: BigDecimal,

    @Column(name = "startsat", nullable = false)
    val startAt: Instant,

    @Column(name = "endsat", nullable = false)
    val endAt: Instant,

    @Column(nullable = false)
    val active: Boolean,

    @Column(nullable = false)
    val priority: Int,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant? = null,

    @Column(name = "updated_at")
    val updatedAt: Instant? = null
)