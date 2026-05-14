package com.hallisanthe.ui.upload

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.hallisanthe.R
import com.hallisanthe.databinding.ActivityUploadProductBinding
import com.hallisanthe.model.Category
import com.hallisanthe.model.Product
import com.hallisanthe.repository.ProductRepository
import com.hallisanthe.repository.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.Locale

class UploadProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadProductBinding
    private var selectedImageUri: Uri? = null
    private val repo by lazy { ProductRepository(this) }
    private val locationManager by lazy { getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    private var capturedLat: Double = 0.0
    private var capturedLng: Double = 0.0

    private val requestLocationPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { grants ->
        val granted = grants[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            grants[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) {
            captureLocation()
        } else {
            Toast.makeText(this, "Location permission is required to capture map coordinates", Toast.LENGTH_LONG).show()
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImageUri = result.data?.data
            selectedImageUri?.let {
                Glide.with(this).load(it).centerCrop().into(binding.ivPreview)
                binding.tvPickImage.visibility = View.GONE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.upload_product)

        setupCategoryDropdown()
        setupLanguageToggle()

        binding.cardImage.setOnClickListener { openGallery() }
        binding.btnGetLocation.setOnClickListener { captureLocation() }
        binding.btnUpload.setOnClickListener { validateAndUpload() }
    }

    private fun captureLocation() {
        if (!hasLocationPermission()) {
            requestLocationPermission.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            return
        }

        if (!isDeviceLocationEnabled()) {
            showEnableLocationDialog()
            return
        }

        binding.btnGetLocation.isEnabled = false
        binding.btnGetLocation.text = "Capturing current location..."

        try {
            val location = getBestLastKnownLocation()
            if (location != null) {
                applyLocation(location)
                return
            }

            val provider = getEnabledLocationProvider()
            if (provider == null) {
                binding.btnGetLocation.isEnabled = true
                binding.btnGetLocation.text = "Capture Map Location"
                showEnableLocationDialog()
                return
            }

            locationManager.requestSingleUpdate(
                provider,
                object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        applyLocation(location)
                    }
                },
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            binding.btnGetLocation.isEnabled = true
            binding.btnGetLocation.text = "Capture Map Location"
            Toast.makeText(this, "Location permission was not granted", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            binding.btnGetLocation.isEnabled = true
            binding.btnGetLocation.text = "Capture Map Location"
            Toast.makeText(this, "Could not capture location: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun isDeviceLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun showEnableLocationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Turn on location")
            .setMessage("Enable device location to capture map coordinates for this product.")
            .setPositiveButton("Open Settings") { _: DialogInterface, _: Int ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun getBestLastKnownLocation(): Location? {
        val providers = listOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER)
        return providers.mapNotNull { provider ->
            if (locationManager.isProviderEnabled(provider)) locationManager.getLastKnownLocation(provider) else null
        }.maxByOrNull { it.time }
    }

    private fun getEnabledLocationProvider(): String? {
        return when {
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> LocationManager.GPS_PROVIDER
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> LocationManager.NETWORK_PROVIDER
            else -> null
        }
    }

    private fun applyLocation(location: Location) {
        capturedLat = location.latitude
        capturedLng = location.longitude
        binding.btnGetLocation.isEnabled = true
        binding.btnGetLocation.text = "Resolving address..."
        binding.btnGetLocation.setTextColor(resources.getColor(R.color.market_green, null))

        lifecycleScope.launch {
            val address = withContext(Dispatchers.IO) {
                reverseGeocode(capturedLat, capturedLng)
            }

            if (address.isNotBlank()) {
                binding.etSellerLocation.setText(address)
                binding.btnGetLocation.text = "Location Captured"
                Toast.makeText(this@UploadProductActivity, "Location captured: $address", Toast.LENGTH_LONG).show()
            } else {
                binding.btnGetLocation.text = "Location Captured: %.5f, %.5f".format(capturedLat, capturedLng)
                Toast.makeText(
                    this@UploadProductActivity,
                    "Coordinates captured, but address lookup failed. Check internet/location services.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun reverseGeocode(latitude: Double, longitude: Double): String {
        val androidAddress = try {
            val geocoder = Geocoder(this, Locale.getDefault())
            @Suppress("DEPRECATION")
            val result = geocoder.getFromLocation(latitude, longitude, 1)?.firstOrNull()
            result?.getAddressLine(0).orEmpty()
        } catch (_: Exception) {
            ""
        }

        return androidAddress.ifBlank {
            reverseGeocodeFromNetwork(latitude, longitude)
        }
    }

    private fun reverseGeocodeFromNetwork(latitude: Double, longitude: Double): String {
        return try {
            val url = URL(
                "https://nominatim.openstreetmap.org/reverse?format=jsonv2" +
                    "&lat=$latitude&lon=$longitude&zoom=18&addressdetails=1"
            )
            val connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                connectTimeout = 7000
                readTimeout = 7000
                setRequestProperty("User-Agent", "HalliSanthe/1.0 Android")
            }

            connection.inputStream.bufferedReader().use { reader ->
                JSONObject(reader.readText()).optString("display_name")
            }
        } catch (_: Exception) {
            ""
        }
    }

    private fun setupLanguageToggle() {
        binding.btnToggleLanguages.setOnClickListener {
            if (binding.layoutOtherLanguages.visibility == View.GONE) {
                binding.layoutOtherLanguages.visibility = View.VISIBLE
                binding.btnToggleLanguages.text = "- Hide other languages"
            } else {
                binding.layoutOtherLanguages.visibility = View.GONE
                binding.btnToggleLanguages.text = "+ Add name in other languages (Optional)"
            }
        }
    }

    private fun setupCategoryDropdown() {
        val categories = Category.values()
            .filter { it != Category.ALL }
            .map { "${it.emoji} ${it.displayName}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
        binding.actvCategory.setAdapter(adapter)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImage.launch(intent)
    }

    private fun validateAndUpload() {
        val name     = binding.etProductName.text.toString().trim()
        var nameKn   = binding.etProductNameKn.text.toString().trim()
        var nameHi   = binding.etProductNameHi.text.toString().trim()
        var nameTa   = binding.etProductNameTa.text.toString().trim()
        val priceStr = binding.etPrice.text.toString().trim()
        val desc     = binding.etDescription.text.toString().trim()
        val catStr   = binding.actvCategory.text.toString().trim()
        val seller   = binding.etSellerName.text.toString().trim()
        val phone    = binding.etSellerPhone.text.toString().trim()
        val location = binding.etSellerLocation.text.toString().trim()
        val stockStr = binding.etStock.text.toString().trim()

        if (name.isEmpty()) { binding.tilProductName.error = "Required"; return }
        if (priceStr.isEmpty()) { binding.tilPrice.error = "Required"; return }
        if (catStr.isEmpty()) { binding.tilCategory.error = "Select category"; return }
        if (seller.isEmpty()) { binding.tilSellerName.error = "Required"; return }

        val price = priceStr.toDoubleOrNull() ?: run {
            binding.tilPrice.error = "Invalid price"; return
        }
        val stock = stockStr.toIntOrNull() ?: 0

        val currentLang = resources.configuration.locale.language
        if (nameKn.isEmpty() && currentLang == "kn") nameKn = name
        if (nameHi.isEmpty() && currentLang == "hi") nameHi = name
        if (nameTa.isEmpty() && currentLang == "ta") nameTa = name

        val cat = Category.values().firstOrNull { catStr.contains(it.displayName) }?.name ?: "POTTERY"

        val product = Product(
            name = name, nameKn = nameKn, nameHi = nameHi, nameTa = nameTa,
            price = price, description = desc, category = cat,
            sellerName = seller, sellerPhone = phone, sellerLocation = location,
            latitude = capturedLat, longitude = capturedLng,
            stock = stock
        )

        binding.btnUpload.isEnabled = false
        binding.progressUpload.visibility = View.VISIBLE

        lifecycleScope.launch {
            val result = if (selectedImageUri != null) {
                repo.uploadProduct(product, selectedImageUri!!)
            } else {
                repo.uploadProductNoImage(product)
            }

            binding.btnUpload.isEnabled = true
            binding.progressUpload.visibility = View.GONE

            when (result) {
                is Result.Success -> {
                    Toast.makeText(this@UploadProductActivity, "✅ Product listed successfully!", Toast.LENGTH_LONG).show()
                    finish()
                }
                is Result.Error -> {
                    Toast.makeText(this@UploadProductActivity, "❌ ${result.message}", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { onBackPressedDispatcher.onBackPressed(); return true }
        return super.onOptionsItemSelected(item)
    }
}
