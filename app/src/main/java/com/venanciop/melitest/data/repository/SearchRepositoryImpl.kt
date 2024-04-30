package com.venanciop.melitest.data.repository

import android.util.Log
import com.venanciop.melitest.data.Result
import com.venanciop.melitest.data.api.model.toDomainModel
import com.venanciop.melitest.data.api.service.ApiService
import com.venanciop.melitest.domain.model.ProductDomainModel
import com.venanciop.melitest.domain.repository.SearchRepository

class SearchRepositoryImpl(private val apiService: ApiService) : SearchRepository {

    override suspend fun getProducts(
        siteId: String,
        query: String,
        position: Int
    ): Result<ProductDomainModel> {

        try {
            val response = apiService.getProducts(
                siteId = siteId,
                query = query,
                offset = position
            )

            return if (response.isSuccessful && response.body() != null) {
                response.body()?.results?.toString()?.let { Log.d("Result" , it) }
                val data = response.body()?.results?.mapNotNull { it?.toDomainModel() } ?: listOf()
                Result.Success(data)
            }else{
                Result.Failure(Exception("Ocurrio un error"))
            }


        } catch (e: Exception) {
            return Result.Failure(e)
        }

    }

}