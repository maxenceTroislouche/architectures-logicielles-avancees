package com.formation.myecommerceapp.ui.page.product.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formation.myecommerceapp.R
import com.formation.myecommerceapp.data.source.ProductDataSource
import com.formation.myecommerceapp.domain.mapper.toProduct
import com.formation.myecommerceapp.domain.mapper.toProductDetails
import com.formation.myecommerceapp.ui.page.product.details.PRODUCT_ID_EXTRA_KEY
import com.formation.myecommerceapp.ui.page.product.details.ProductDetailsActivity
import com.formation.myecommerceapp.ui.page.product.list.state.Product

class ProductListActivity : AppCompatActivity() {
    private fun navigateToDetails(product: Product) {
        val productId = product.id
        val productDetails = ProductDataSource.getProductById(productId)!!.toProductDetails()
        val intent = Intent(this@ProductListActivity, ProductDetailsActivity::class.java)
        intent.putExtra(PRODUCT_ID_EXTRA_KEY, productDetails.id.toString())
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_list)
        val products = ProductDataSource.getProducts().map { it.toProduct() }
        findViewById<RecyclerView>(R.id.product_list).apply {
            adapter = ProductAdapter(products, this@ProductListActivity::navigateToDetails)
            layoutManager = LinearLayoutManager(context)
        }
    }
}