package com.formation.myecommerceapp.ui.page.product.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.formation.myecommerceapp.R
import com.formation.myecommerceapp.data.source.ProductDataSource
import com.formation.myecommerceapp.databinding.ActivityProductDetailsBinding
import com.formation.myecommerceapp.domain.mapper.toProductDetails
import com.formation.myecommerceapp.ui.page.product.details.state.ProductDetails
import java.util.UUID

const val PRODUCT_ID_EXTRA_KEY = "PRODUCT_ID"

class ProductDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)

        val productIdString = intent.getStringExtra(PRODUCT_ID_EXTRA_KEY)
        val productId = UUID.fromString(productIdString)
        val productDetails = ProductDataSource.getProductById(productId)!!.toProductDetails()
        bind(productDetails)
        setContentView(binding.root)
    }

    private fun bind(productDetails: ProductDetails) {
        binding.productDetailsName.text = productDetails.name
        binding.productDetailsDescription.text = productDetails.description
        binding.productDetailsPrice.text = "${productDetails.price}€"
        binding.productDetailsCategory.text = productDetails.category
        binding.productDetailsNoteString.text = "${productDetails.averageRate}/5 sur ${productDetails.rateCount} avis"
        // Retour en arrière lors du clic sur la flèche
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        val notes = listOf(binding.productDetailsNote1, binding.productDetailsNote2,
            binding.productDetailsNote3, binding.productDetailsNote4, binding.productDetailsNote5)

        var count = 1
        notes.forEach { note ->
            note.setImageResource(
                when {
                    productDetails.averageRate >= count -> R.drawable.ic_star_filled
                    productDetails.averageRate >= count - 0.5 -> R.drawable.ic_star_half_filled
                    else -> R.drawable.ic_star_outlined
                }
            )
            count++
        }
    }
}