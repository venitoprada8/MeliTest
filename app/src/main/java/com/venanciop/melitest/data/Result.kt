package com.venanciop.melitest.data


sealed class Result<out T> {

    data class Success<T>(val data: List<T>?) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()

}