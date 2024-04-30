package com.venanciop.melitest.data.api.model


import com.venanciop.melitest.domain.model.ProductDomainModel
import com.squareup.moshi.Json
import com.venanciop.melitest.data.utils.formatPrice

data class ResultsItem(

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "price")
    val price: Float? = null,

    @Json(name = "currency_id")
    val currency_id: String? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "thumbnail")
    val thumbnail: String? = null,

    @Json(name = "condition")
    val condition: String,

    @Json(name = "available_quantity")
    val available_quantity: Int,

    )

fun ResultsItem.toDomainModel(): ProductDomainModel {

    return ProductDomainModel(
        productImage = this.thumbnail ?: "img vacia...",
        title = this.title ?: "Titulo Vacio",
        id = this.id ?: "Id Vacio",
        price = formatPrice(price = this.price, currencyId = this.currency_id),
        condition = this.condition ?: "Sin informacion",
        availableQuantity = this.available_quantity,
    )
}