package com.hallisanthe.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.hallisanthe.databinding.ActivityProfileBinding
import com.hallisanthe.repository.ProductRepository
import com.hallisanthe.repository.Result
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val auth = FirebaseAuth.getInstance()
    private val repo by lazy { ProductRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupUI()
    }

    private fun setupUI() {
        val user = auth.currentUser
        if (user != null) {
            binding.tvUserName.text = user.displayName ?: "Artisan"
            binding.tvUserEmail.text = user.email
            binding.tvProfileInitial.text = (user.displayName ?: user.email ?: "A").take(1).uppercase()
            
            binding.btnLogout.visibility = View.VISIBLE
            binding.btnLogin.visibility = View.GONE
        } else {
            binding.tvUserName.text = "Guest User"
            binding.tvUserEmail.text = "Login to sell and sync wishlist"
            binding.tvProfileInitial.text = "?"
            
            binding.btnLogout.visibility = View.GONE
            binding.btnLogin.visibility = View.VISIBLE
        }

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnAbout.setOnClickListener {
            Toast.makeText(this, "Halli-Santhe v1.0\nEmpowering Village Artisans", Toast.LENGTH_LONG).show()
        }
        
        binding.btnSettings.setOnClickListener {
            Toast.makeText(this, "Settings feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        binding.btnMyProducts.setOnClickListener {
            if (user == null) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                showMyProducts()
            }
        }
    }

    private fun showMyProducts() {
        lifecycleScope.launch {
            Toast.makeText(this@ProfileActivity, "Fetching your listed items...", Toast.LENGTH_SHORT).show()
            when (val result = repo.getMyProducts()) {
                is Result.Success -> {
                    val products = result.data
                    val items = if (products.isEmpty()) {
                        arrayOf("No products listed yet")
                    } else {
                        products.map { "${it.name} - Rs ${String.format("%.0f", it.price)}" }.toTypedArray()
                    }
                    AlertDialog.Builder(this@ProfileActivity)
                        .setTitle("My Listed Products")
                        .setItems(items, null)
                        .setPositiveButton("OK", null)
                        .show()
                }
                is Result.Error -> Toast.makeText(this@ProfileActivity, result.message, Toast.LENGTH_LONG).show()
                else -> {}
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}
