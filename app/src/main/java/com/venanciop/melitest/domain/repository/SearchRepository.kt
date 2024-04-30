package com.venanciop.melitest.domain.repository

import com.venanciop.melitest.domain.model.ProductDomainModel
import com.venanciop.melitest.data.Result

interface SearchRepository {

    suspend fun getProducts(
        siteId: String,
        query: String,
        position: Int
    ): Result<ProductDomainModel>


}