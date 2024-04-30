package com.venanciop.melitest.domain

import com.venanciop.melitest.domain.model.ProductDomainModel
import com.venanciop.melitest.data.Result
import com.venanciop.melitest.domain.repository.SearchRepository

class GetProducts(private val searchRepository: SearchRepository) {


    suspend fun invoke(query: String, siteId: String, position: Int): Result<ProductDomainModel> = getProducts(siteId, query, position)


    private suspend fun getProducts(
        site: String,
        query: String,
        position: Int
    ): Result<ProductDomainModel> {

        return searchRepository.getProducts(site, query, position)

    }

}