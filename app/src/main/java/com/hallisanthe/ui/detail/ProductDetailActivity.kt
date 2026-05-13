package com.hallisanthe.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hallisanthe.R
import com.hallisanthe.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadProductData()
    }

    private fun loadProductData() {
        val name        = intent.getStringExtra("PRODUCT_NAME") ?: ""
        val price       = intent.getDoubleExtra("PRODUCT_PRICE", 0.0)
        val desc        = intent.getStringExtra("PRODUCT_DESC") ?: ""
        val imageUrl    = intent.getStringExtra("PRODUCT_IMAGE") ?: ""
        val category    = intent.getStringExtra("PRODUCT_CATEGORY") ?: ""
        val sellerName  = intent.getStringExtra("SELLER_NAME") ?: ""
        val sellerPhone = intent.getStringExtra("SELLER_PHONE") ?: ""
        val sellerLoc   = intent.getStringExtra("SELLER_LOCATION") ?: ""
        val nameKn      = intent.getStringExtra("PRODUCT_NAME_KN") ?: ""
        val nameHi      = intent.getStringExtra("PRODUCT_NAME_HI") ?: ""
        val nameTa      = intent.getStringExtra("PRODUCT_NAME_TA") ?: ""
        val stock       = intent.getIntExtra("PRODUCT_STOCK", 0)
        val lat         = intent.getDoubleExtra("LATITUDE", 0.0)
        val lng         = intent.getDoubleExtra("LONGITUDE", 0.0)

        val lang = resources.configuration.locale.language
        val displayName = when (lang) {
            "kn" -> if (nameKn.isNotEmpty()) nameKn else name
            "hi" -> if (nameHi.isNotEmpty()) nameHi else name
            "ta" -> if (nameTa.isNotEmpty()) nameTa else name
            else -> name
        }

        supportActionBar?.title = displayName

        // Main info
        binding.tvProductName.text = displayName
        binding.tvPrice.text = "₹ ${String.format("%.2f", price)}"
        binding.tvDescription.text = desc
        binding.tvCategory.text = "📦 $category"
        binding.tvStock.text = if (stock > 0) "✅ In Stock ($stock available)" else "❌ Out of Stock"
        binding.tvStock.setTextColor(
            resources.getColor(if (stock > 0) R.color.market_green else R.color.market_red, null)
        )

        // Seller info
        binding.tvSellerName.text = "👤 $sellerName"
        binding.tvSellerLocation.text = "📍 $sellerLoc"
        
        if (lat != 0.0 && lng != 0.0) {
            binding.btnViewMap.visibility = View.VISIBLE
            binding.btnViewMap.setOnClickListener {
                val gmmIntentUri = Uri.parse("geo:$lat,$lng?q=$lat,$lng($sellerName's Location)")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                if (mapIntent.resolveActivity(packageManager) != null) {
                    startActivity(mapIntent)
                } else {
                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=$lat,$lng"))
                    startActivity(webIntent)
                }
            }
        }

        // Product image
        if (imageUrl.isNotEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_product_placeholder)
                .error(R.drawable.ic_product_placeholder)
                .into(binding.ivProductImage)
        } else {
            binding.ivProductImage.setImageResource(R.drawable.ic_product_placeholder)
        }

        // CTA Buttons
        binding.btnEnquire.setOnClickListener {
            sendEnquiry(sellerPhone, displayName)
        }

        binding.btnCall.setOnClickListener {
            callSeller(sellerPhone)
        }

        binding.btnShare.setOnClickListener {
            shareProduct(displayName, price, sellerName)
        }

        // Hide call/enquire if no phone
        if (sellerPhone.isEmpty()) {
            binding.btnCall.visibility = View.GONE
            binding.btnEnquire.visibility = View.GONE
        }
    }

    private fun sendEnquiry(phone: String, productName: String) {
        try {
            val msg = getString(R.string.enquiry_message, productName)
            val uri = Uri.parse("https://wa.me/91$phone?text=${Uri.encode(msg)}")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        } catch (e: Exception) {
            try {
                val smsUri = Uri.parse("smsto:$phone")
                val intent = Intent(Intent.ACTION_SENDTO, smsUri)
                intent.putExtra("sms_body", getString(R.string.enquiry_message, productName))
                startActivity(intent)
            } catch (ex: Exception) {
                Toast.makeText(this, "Unable to open messaging app", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun callSeller(phone: String) {
        try {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
        } catch (e: Exception) {
            Toast.makeText(this, "Cannot make call", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareProduct(name: String, price: Double, seller: String) {
        val text = "🛒 Check out '$name' for ₹${String.format("%.0f", price)} on Halli-Santhe!\nSeller: $seller\n#VocalForLocal #HalliSanthe"
        startActivity(Intent.createChooser(
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }, "Share Product"
        ))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { onBackPressedDispatcher.onBackPressed(); return true }
        return super.onOptionsItemSelected(item)
    }
}
