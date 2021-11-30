package com.app.depopapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.depopapp.R
import com.app.depopapp.adapters.ProductViewAdapter
import com.app.depopapp.models.ProductList
import com.app.depopapp.utils.isInternet
import com.app.depopapp.viewmodel.MainActivityViewModel

/*
*  This fragment used to populate product list in recycle view
* */
class ProductListFragment : Fragment() {
    private lateinit var productViewAdapter: ProductViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProductListFragment().apply {
            }
    }

    private fun initViewModel(view: View) {
        val recycleView = view.findViewById<RecyclerView>(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(activity)
        productViewAdapter = ProductViewAdapter()
        recycleView.adapter = productViewAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getProductListObserver().observe(viewLifecycleOwner, Observer<ProductList> {
            if (it != null) {
                productViewAdapter.setData(it.objects)
            } else {
                Toast.makeText(activity,"Data not found...", Toast.LENGTH_SHORT).show()
            }
        })
        if (isInternet(activity)) {
            viewModel.webApiCall()
        } else {
            Toast.makeText(activity, "There is no internet connection...", Toast.LENGTH_SHORT)
                .show()
        }
    }
}