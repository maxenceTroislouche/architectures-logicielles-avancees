package com.formation.myecommerceapp.ui.page.product.list

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formation.myecommerceapp.R
import com.formation.myecommerceapp.data.source.ProductDataSource
import com.formation.myecommerceapp.domain.mapper.toProduct

class ProductListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_list)
        val products = ProductDataSource.getProducts().map { it.toProduct() }
        findViewById<RecyclerView>(R.id.product_list).apply {
            adapter = ProductAdapter(products)
            layoutManager = LinearLayoutManager(context)
        }
    }
}