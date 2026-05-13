package com.hallisanthe.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.google.firebase.auth.FirebaseAuth
import com.hallisanthe.R
import com.hallisanthe.adapter.ProductAdapter
import com.hallisanthe.databinding.ActivityMainBinding
import com.hallisanthe.model.Category
import com.hallisanthe.model.Product
import com.hallisanthe.ui.detail.ProductDetailActivity
import com.hallisanthe.ui.upload.UploadProductActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: ProductAdapter
    private val auth = FirebaseAuth.getInstance()
    private var showingWishlist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        updateTitle()
        setupRecyclerView()
        setupLanguageChips()
        setupCategoryChips()
        setupObservers()
        setupSwipeRefresh()
        setupBottomNavigation()
    }

    private fun updateTitle() {
        val user = auth.currentUser
        if (user != null) {
            val name = user.displayName ?: user.email?.split("@")?.get(0) ?: "Artisan"
            binding.toolbar.subtitle = "Hello, $name! 👋"
        } else {
            binding.toolbar.subtitle = getString(R.string.tagline)
        }
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter(
            onWishlistClick = { product -> viewModel.toggleWishlist(product) },
            onClick = { product -> openDetail(product) }
        )
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
        }
    }

    private fun setupLanguageChips() {
        val languages = listOf(
            Triple("en", "English", "🇺🇸"),
            Triple("kn", "ಕನ್ನಡ", "🇮🇳"),
            Triple("hi", "हिन्दी", "🇮🇳"),
            Triple("ta", "தமிழ்", "🇮🇳")
        )

        val currentLang = resources.configuration.locale.language

        languages.forEach { (code, name, flag) ->
            val chip = Chip(this).apply {
                text = "$flag $name"
                isCheckable = true
                setChipBackgroundColorResource(R.color.chip_background_selector)
                setTextColor(resources.getColorStateList(R.color.chip_text_selector, null))
                isChecked = (code == currentLang) || (code == "en" && currentLang != "kn" && currentLang != "hi" && currentLang != "ta")
            }
            chip.setOnClickListener {
                if (currentLang != code) {
                    setAppLocale(code)
                }
            }
            binding.chipGroupLanguages.addView(chip)
        }
    }

    private fun setupCategoryChips() {
        Category.values().forEach { cat ->
            val chip = Chip(this).apply {
                text = "${cat.emoji} ${cat.displayName}"
                isCheckable = true
                setChipBackgroundColorResource(R.color.chip_background_selector)
                setTextColor(resources.getColorStateList(R.color.chip_text_selector, null))
                if (cat == Category.ALL) isChecked = true
            }
            chip.setOnClickListener {
                showingWishlist = false
                viewModel.loadProducts(cat.name)
            }
            binding.chipGroupCategories.addView(chip)
        }
    }

    private fun setupObservers() {
        viewModel.products.observe(this) { products ->
            val visibleProducts = if (showingWishlist) products.filter { it.isWishlisted } else products
            adapter.submitList(visibleProducts)
            binding.layoutEmpty.visibility = if (visibleProducts.isEmpty()) View.VISIBLE else View.GONE
            binding.rvProducts.visibility = if (visibleProducts.isEmpty()) View.GONE else View.VISIBLE
        }
        viewModel.loading.observe(this) { loading ->
            binding.swipeRefresh.isRefreshing = loading
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }
        viewModel.isEmpty.observe(this) { empty ->
            if (!showingWishlist) {
                binding.layoutEmpty.visibility = if (empty) View.VISIBLE else View.GONE
                binding.rvProducts.visibility = if (empty) View.GONE else View.VISIBLE
            }
        }
        viewModel.error.observe(this) { error ->
            error?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeResources(
            R.color.market_primary, R.color.market_accent,
            R.color.market_primary_dark, R.color.market_accent_vibrant
        )
        binding.swipeRefresh.setOnRefreshListener { viewModel.refresh() }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showingWishlist = false
                    binding.rvProducts.smoothScrollToPosition(0)
                    viewModel.loadProducts("ALL")
                    true
                }
                R.id.nav_categories -> {
                    // Scroll to top or just show category selection logic
                    binding.appBarLayout.setExpanded(true)
                    Toast.makeText(this, "Select a category above 🏺", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_upload -> {
                    if (auth.currentUser == null) {
                        Toast.makeText(this, "Please login to sell products", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        startActivity(Intent(this, UploadProductActivity::class.java))
                    }
                    false
                }
                R.id.nav_wishlist -> {
                    showingWishlist = true
                    viewModel.loadProducts("ALL")
                    Toast.makeText(this, "Showing Wishlist ❤️", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    false
                }
                else -> false
            }
        }

        binding.fabSell.setOnClickListener {
             if (auth.currentUser == null) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, UploadProductActivity::class.java))
            }
        }
        
        binding.btnSeedDemo.setOnClickListener {
            viewModel.seedDemo()
            binding.btnSeedDemo.visibility = View.GONE
        }
    }

    private fun showAccountOptions() {
        val user = auth.currentUser
        val options = if (user != null) {
            arrayOf(
                "👤 Name: ${user.displayName ?: "N/A"}",
                "📧 Email: ${user.email}",
                "🚪 Logout",
                "❌ Cancel"
            )
        } else {
            arrayOf("🔑 Login / Register", "❌ Cancel")
        }

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("My Account")
            .setItems(options) { _, which ->
                if (user != null) {
                    if (which == 2) doLogout()
                } else {
                    if (which == 0) startActivity(Intent(this, LoginActivity::class.java))
                }
            }.show()
    }

    private fun openDetail(product: Product) {
        val intent = Intent(this, ProductDetailActivity::class.java).apply {
            putExtra("PRODUCT_ID", product.id)
            putExtra("PRODUCT_NAME", product.name)
            putExtra("PRODUCT_PRICE", product.price)
            putExtra("PRODUCT_DESC", product.description)
            putExtra("PRODUCT_IMAGE", product.imageUrl)
            putExtra("PRODUCT_CATEGORY", product.category)
            putExtra("SELLER_NAME", product.sellerName)
            putExtra("SELLER_PHONE", product.sellerPhone)
            putExtra("SELLER_LOCATION", product.sellerLocation)
            putExtra("LATITUDE", product.latitude)
            putExtra("LONGITUDE", product.longitude)
            putExtra("PRODUCT_NAME_KN", product.nameKn)
            putExtra("PRODUCT_NAME_HI", product.nameHi)
            putExtra("PRODUCT_NAME_TA", product.nameTa)
            putExtra("PRODUCT_STOCK", product.stock)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query); return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) viewModel.refresh()
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_language -> {
                showLanguageDialog(); true
            }
            R.id.action_logout -> {
                doLogout(); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun doLogout() {
        auth.signOut()
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showLanguageDialog() {
        val langs = arrayOf("English", "ಕನ್ನಡ (Kannada)", "हिंदी (Hindi)", "தமிழ் (Tamil)")
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("🌐 Select Language")
            .setItems(langs) { _, which ->
                val locale = when (which) {
                    1 -> "kn"; 2 -> "hi"; 3 -> "ta"; else -> "en"
                }
                setAppLocale(locale)
            }.show()
    }

    private fun setAppLocale(lang: String) {
        val appLocale = androidx.core.os.LocaleListCompat.forLanguageTags(lang)
        androidx.appcompat.app.AppCompatDelegate.setApplicationLocales(appLocale)
    }

    override fun onResume() {
        super.onResume()
        updateTitle()
        viewModel.refresh()
    }
}
