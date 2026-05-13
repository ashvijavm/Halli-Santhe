package com.hallisanthe.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.hallisanthe.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val auth = FirebaseAuth.getInstance()

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
                Toast.makeText(this, "Fetching your listed items...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { finish(); return true }
        return super.onOptionsItemSelected(item)
    }
}
