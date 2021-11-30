package com.app.depopapp.models

import java.io.Serializable
import kotlin.collections.ArrayList

/*
    Response model data classes
* */
data class ProductList(val objects: ArrayList<ProductData>) : Serializable
data class ProductData(
    val user_id: Int,
    val description: String,
    val status: String,
    val pictures_data: ArrayList<PictureData>
) : Serializable

data class PictureData(
    val formats: ImageList
) : Serializable

data class ImageList(
    val P1: ImageDetail,
    val P2: ImageDetail,
    val P4: ImageDetail,
    val P5: ImageDetail,
    val P6: ImageDetail,
    val P7: ImageDetail,
    val P8: ImageDetail
) : Serializable

data class ImageDetail(
    val url: String,
    val height: Int,
    val weight: Int
) : Serializable