package com.venanciop.melitest.presentation.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.venanciop.melitest.domain.GetProducts

class ProductListViewModelFactory(private val getProducts: GetProducts) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetProducts::class.java).newInstance(getProducts)
    }
}