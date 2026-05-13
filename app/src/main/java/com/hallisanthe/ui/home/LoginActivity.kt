package com.hallisanthe.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.hallisanthe.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()
    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // If already logged in, skip
        if (auth.currentUser != null) {
            goToMain(); return
        }

        setupUI()
    }

    private fun setupUI() {
        binding.btnLogin.setOnClickListener {
            if (isLoginMode) doLogin() else toggleMode()
        }

        binding.btnRegister.setOnClickListener {
            if (!isLoginMode) doRegister() else toggleMode()
        }

        binding.btnGuest.setOnClickListener { goToMain() }
    }

    private fun toggleMode() {
        isLoginMode = !isLoginMode
        if (isLoginMode) {
            binding.tilName.visibility = View.GONE
            binding.btnLogin.text = "Login"
            binding.btnRegister.text = "Switch to Register"
        } else {
            binding.tilName.visibility = View.VISIBLE
            binding.btnLogin.text = "Switch to Login"
            binding.btnRegister.text = "Create Account"
        }
    }

    private fun doLogin() {
        val email = binding.etEmail.text.toString().trim()
        val pass  = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressLogin.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false

        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener { 
                goToMain() 
            }
            .addOnFailureListener { e ->
                binding.progressLogin.visibility = View.GONE
                binding.btnLogin.isEnabled = true
                Toast.makeText(this, "Login failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun doRegister() {
        val name  = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val pass  = binding.etPassword.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressLogin.visibility = View.VISIBLE
        binding.btnRegister.isEnabled = false

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener { result ->
                // Update display name
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
                
                result.user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener {
                        goToMain()
                    }
            }
            .addOnFailureListener { e ->
                binding.progressLogin.visibility = View.GONE
                binding.btnRegister.isEnabled = true
                Toast.makeText(this, "Registration failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
