package com.app.depopapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.depopapp.ProductDetailActivity
import com.app.depopapp.R
import com.app.depopapp.models.ProductData
import com.squareup.picasso.Picasso

/*
    Product list adapter to display rows with recycler view
* */
class ProductViewAdapter : RecyclerView.Adapter<ProductViewAdapter.ProductViewHolder>() {
    var items = ArrayList<ProductData>()
    fun setData(items: ArrayList<ProductData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val txtId = view.findViewById<TextView>(R.id.txtId)
        val txtDescription = view.findViewById<TextView>(R.id.txtDescription)

        fun bind(productData : ProductData) {
            txtId.text = productData.user_id.toString()
            txtDescription.text = productData.description
            Picasso.get().load(productData.pictures_data[0].formats.P1.url).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_row, parent, false)
        return ProductViewHolder(view)
    }

    // open product detail activity on click of single row item from list
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.itemView.setOnClickListener { v ->
            val intent = Intent(v.context, ProductDetailActivity::class.java)
            intent.putExtra("product_list", items[position])
            v.context.startActivity(intent)
        }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}