package com.venanciop.melitest.presentation.product_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venanciop.melitest.domain.model.ProductDomainModel
import com.venanciop.melitest.domain.GetProducts
import com.venanciop.melitest.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductListViewModel(private val getProducts: GetProducts) : ViewModel() {

    private var siteId: String = "MCO"
    private var position : Int = 1

    private val _products = MutableLiveData<List<ProductDomainModel>>().apply { value = emptyList() }
    val products: LiveData<List<ProductDomainModel>> = _products

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList


    fun getProducts(query : String ) {

        _isViewLoading.value = true

        viewModelScope.launch {

            val result: Result<ProductDomainModel> = withContext(Dispatchers.IO){
                getProducts.invoke(query, siteId, position)
            }

            _isViewLoading.value = false

            when(result){
                is Result.Success ->{
                    if(result.data.isNullOrEmpty()){
                        _isEmptyList.value = true
                        _products.value = emptyList()
                    }else{
                        _isEmptyList.value = false
                        _products.value = result.data
                    }
                }
                is Result.Failure ->{
                    _onMessageError.value = result.exception
                }
            }

        }

    }

}


