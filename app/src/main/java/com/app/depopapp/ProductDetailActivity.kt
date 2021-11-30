package com.app.depopapp

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.app.depopapp.databinding.ItemProductBinding
import com.app.depopapp.databinding.ProductDetailActivityBinding
import com.app.depopapp.models.ProductData
import com.limerse.slider.listener.CarouselListener
import com.limerse.slider.model.CarouselItem
import com.limerse.slider.utils.setImage

/*
    This is product detail class to display multiple images of same products
    also it display user Id and description in detail with scroll view
    product images bind with 3rd party libs which is called as slider module
* */
class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ProductDetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val productData = intent.getSerializableExtra("product_list") as ProductData?
        val imageList = mutableListOf<CarouselItem>()
        if (productData != null) {
            binding.txtId.text = productData.user_id.toString()
            binding.txtDescription.text = productData.description
            for (item in productData.pictures_data.iterator()) {
                var url = item.formats.P8.url
                if(displayMatrix().widthPixels <= 150) {
                    url = item.formats.P2.url
                } else if (displayMatrix().widthPixels in 151..210) {
                    url = item.formats.P4.url
                } else if (displayMatrix().widthPixels in 221..320) {
                    url = item.formats.P5.url
                } else if (displayMatrix().widthPixels in 321..480) {
                    url = item.formats.P6.url
                } else if (displayMatrix().widthPixels in 481..640) {
                    url = item.formats.P1.url
                } else if (displayMatrix().widthPixels in 641..960) {
                    url = item.formats.P7.url
                } else if (displayMatrix().widthPixels in 961..1280) {
                    url = item.formats.P8.url
                }
                imageList.add(CarouselItem(imageUrl = url))
            }
        }
        binding.carousel.registerLifecycle(lifecycle)
        binding.carousel.apply {
            imageScaleType = ImageView.ScaleType.FIT_CENTER
            binding.carousel.carouselListener = object : CarouselListener {
                override fun onCreateViewHolder(
                    layoutInflater: LayoutInflater,
                    parent: ViewGroup
                ): ViewBinding {
                    return ItemProductBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                }

                override fun onBindViewHolder(
                    binding: ViewBinding,
                    item: CarouselItem,
                    position: Int
                ) {
                    val currentBinding = binding as ItemProductBinding

                    currentBinding.imageView.apply {
                        scaleType = imageScaleType

                        setImage(item)
                    }
                }
            }
            binding.carousel.setData(imageList)
        }

    }

    // get width and height of device as per the screen and check it is below api level 30 because of deprecation
    private fun displayMatrix(): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= 30) {
            display?.apply {
                getRealMetrics(displayMetrics)
            }
        } else {
            windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        }
        return displayMetrics
    }
}