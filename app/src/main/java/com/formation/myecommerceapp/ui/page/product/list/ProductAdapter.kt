package com.formation.myecommerceapp.ui.page.product.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.formation.myecommerceapp.R
import com.formation.myecommerceapp.ui.page.product.list.state.Product
import java.util.Locale
import java.text.NumberFormat

class ProductAdapter(
    private val products: List<Product>,
    private val onProductClicked: (product: Product) -> Unit,
): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(products[position])

    inner class ViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById(R.id.product_title)
        private val descriptionView: TextView = itemView.findViewById(R.id.product_description)
        private val statusView: TextView = itemView.findViewById(R.id.product_available)
        private val priceView: TextView = itemView.findViewById(R.id.product_price)
        fun bind(product: Product) {
            nameView.text = product.name
            descriptionView.text = product.description
            val statusStringRes = if (product.isAvailable) R.string.available else R.string.unavailable
            statusView.text = itemView.context.getString(statusStringRes)
            val priceFormatter = NumberFormat.getCurrencyInstance(Locale.FRANCE)
            priceView.text = priceFormatter.format(product.price)
            itemView.rootView.setOnClickListener {
                onProductClicked(product)
            }
        }
    }
}
