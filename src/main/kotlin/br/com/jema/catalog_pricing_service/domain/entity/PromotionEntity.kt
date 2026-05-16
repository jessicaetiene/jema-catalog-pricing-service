package br.com.jema.catalog_pricing_service.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.Instant

@Entity
@Table(name = "tbl_promotions")
class PromotionEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val productId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: PromotionType,

    @Column(name = "value", nullable = false, precision = 10, scale = 2)
    val value: BigDecimal,

    @Column(name = "startat", nullable = false)
    val startAt: Instant,

    @Column(name = "endat", nullable = false)
    val endAt: Instant,

    @Column(nullable = false)
    val active: Boolean,

    @Column(nullable = false)
    val priority: Int
)