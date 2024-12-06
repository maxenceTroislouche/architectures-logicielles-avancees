package com.formation.myecommerceapp.ui.page

import android.accounts.Account
import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.formation.myecommerceapp.R
import com.formation.myecommerceapp.databinding.ActivityMainBinding
import com.formation.myecommerceapp.ui.page.account.AccountFragment
import com.formation.myecommerceapp.ui.page.category.CategoryFragment
import com.formation.myecommerceapp.ui.page.favorite.FavoriteFragment
import com.formation.myecommerceapp.ui.page.home.HomeFragment
import com.formation.myecommerceapp.ui.page.shop.ShopFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialiser la navigation vers le HomeFragment au démarrage
        if (savedInstanceState == null) {
            navigateToDestination(HomeFragment())
        }

        // Gestion des clics sur la BottomNavigationView
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            // Récupérer le Fragment courant
            val currentFragment =
                supportFragmentManager.findFragmentById(binding.fragmentContainer.id)

            // Déterminer la destination
            val destination: Fragment = when (item.itemId) {
                R.id.navigation_category -> CategoryFragment()
                R.id.navigation_favorites -> FavoriteFragment()
                R.id.navigation_account -> AccountFragment()
                R.id.navigation_shop -> ShopFragment()
                else -> HomeFragment()
            }

            // Remplacer uniquement si ce n'est pas le même Fragment
            if (currentFragment == null || currentFragment::class.java != destination::class.java) {
                navigateToDestination(destination)
            }

            return@setOnItemSelectedListener true
        }

        onBackPressedDispatcher.addCallback(this) {
            val currentFragment = supportFragmentManager
                .findFragmentById(binding.fragmentContainer.id)

            if (currentFragment is HomeFragment) {
                // Si HomeFragment alors on quitte l'app
                finish()
            } else {
                navigateToDestination(HomeFragment())
            }
        }
    }

    private fun navigateToDestination(destination: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, destination)
            .commit()

        // Fix menu: Lorsque je fais un retour arrière vers Home, le menu ne s'actualise pas !
        var menuItemId: Int = -1

        when (destination) {
            is HomeFragment -> menuItemId = R.id.navigation_home
            is ShopFragment -> menuItemId = R.id.navigation_shop
            is FavoriteFragment -> menuItemId = R.id.navigation_favorites
            is CategoryFragment -> menuItemId = R.id.navigation_category
            is AccountFragment -> menuItemId = R.id.navigation_account
        }
        if (menuItemId != -1) {
            binding.bottomNavigation.menu.findItem(menuItemId).isChecked = true
        }
    }
}