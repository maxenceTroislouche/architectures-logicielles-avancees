package com.formation.myecommerceapp.ui.page.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.formation.myecommerceapp.databinding.FragmentHomeBinding
import com.formation.myecommerceapp.databinding.ItemCarouselBinding
import java.util.UUID

data class LastPurchasedProduct(
    val id: UUID,
    val name: String,
)

class LastPurchaseAdapter(
    private val items: List<LastPurchasedProduct>
) : RecyclerView.Adapter<LastPurchaseAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LastPurchasedProduct) {
            // binding.itemImage.setImageResource(item.imageResId) // Exemple
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = listOf(
            LastPurchasedProduct(id=UUID.randomUUID(), name="Produit 1"),
            LastPurchasedProduct(id=UUID.randomUUID(), name="Produit 2"),
            LastPurchasedProduct(id=UUID.randomUUID(), name="Produit 3"),
        )
        binding.recyclerViewCarousel.adapter = LastPurchaseAdapter(products)
    }
}