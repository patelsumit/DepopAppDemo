package com.app.depopapp.networkcall

import com.app.depopapp.models.ProductList
import retrofit2.http.GET
import retrofit2.http.Query
/*
    Service method in which we can get the list of products from JSON response
    where offset_id is parameter which we have passe
* */
interface Service {
    @GET("popular/")
    suspend fun getProductList(@Query ("offset_id") query : String) : ProductList
}