package com.app.depopapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.depopapp.models.ProductList
import com.app.depopapp.networkcall.Service
import com.app.depopapp.networkcall.ServiceEndPointInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
    View model class of main activity to get product data from service
    by using kotlin coroutines
* */
class MainActivityViewModel : ViewModel() {
    private var productListLiveData : MutableLiveData<ProductList> = MutableLiveData()

    fun getProductListObserver() : MutableLiveData<ProductList> {
        return productListLiveData
    }

    fun webApiCall () {
        viewModelScope.launch(Dispatchers.IO) {
            val serviceInstance = ServiceEndPointInstance.getInstance().create(Service::class.java)
            val response = serviceInstance.getProductList("10")
            productListLiveData.postValue(response)
        }
    }
}
