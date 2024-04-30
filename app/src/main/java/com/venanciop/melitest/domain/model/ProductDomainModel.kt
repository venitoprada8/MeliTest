package com.venanciop.melitest.domain.model

data class ProductDomainModel(
    val productImage: String,
    val title: String,
    val price: String,
    val condition : String,
    val availableQuantity : Int,
    val id: String
)