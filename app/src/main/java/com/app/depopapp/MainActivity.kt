package com.app.depopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.depopapp.fragments.ProductListFragment

/*
    Created By : Sumit Patel
    Date : 15th - Sep
* */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setProductList()
    }

    // create fragment with product list
    private fun setProductList() {
        val fragment = ProductListFragment.newInstance()
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.linLayout, fragment)
        fragmentTransaction.commit()
    }
}