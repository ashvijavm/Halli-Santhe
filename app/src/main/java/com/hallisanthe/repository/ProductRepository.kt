package com.hallisanthe.repository

import android.content.Context
import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.hallisanthe.model.AppDatabase
import com.hallisanthe.model.Product
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

class ProductRepository(private val context: Context) {

    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val localDb = AppDatabase.getInstance(context)
    private val COLLECTION = "products"

    suspend fun getProducts(category: String = "ALL"): Result<List<Product>> =
        withContext(Dispatchers.IO) {
            try {
                val query: Query = if (category == "ALL")
                    db.collection(COLLECTION).orderBy("timestamp", Query.Direction.DESCENDING)
                else
                    db.collection(COLLECTION)
                        .whereEqualTo("category", category)
                        .orderBy("timestamp", Query.Direction.DESCENDING)

                val snapshot = query.limit(100).get().await()
                val products = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(Product::class.java)?.copy(id = doc.id)
                }
                localDb.productDao().clearAll()
                localDb.productDao().insertAll(products)
                Result.Success(products)
            } catch (e: Exception) {
                val cached = if (category == "ALL")
                    localDb.productDao().getAllProducts()
                else
                    localDb.productDao().getByCategory(category)
                if (cached.isNotEmpty()) Result.Success(cached)
                else Result.Error(e.message ?: "Failed to load products")
            }
        }

    suspend fun searchProducts(query: String): Result<List<Product>> =
        withContext(Dispatchers.IO) {
            try {
                val snapshot = db.collection(COLLECTION)
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .limit(200).get().await()
                val all = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(Product::class.java)?.copy(id = doc.id)
                }
                val q = query.lowercase()
                val filtered = all.filter { p ->
                    p.name.lowercase().contains(q) ||
                    p.nameKn.lowercase().contains(q) ||
                    p.nameHi.lowercase().contains(q) ||
                    p.nameTa.lowercase().contains(q) ||
                    p.category.lowercase().contains(q) ||
                    p.description.lowercase().contains(q)
                }
                Result.Success(filtered)
            } catch (e: Exception) {
                val local = localDb.productDao().search(query)
                Result.Success(local)
            }
        }

    suspend fun uploadProduct(product: Product, imageUri: Uri): Result<String> =
        withContext(Dispatchers.IO) {
            try {
                val file = getFileFromUri(imageUri) ?: throw Exception("Could not process image")
                
                val compressed = Compressor.compress(context, file) {
                    resolution(800, 800)
                    quality(75)
                }

                val imageRef = storage.reference.child("products/${UUID.randomUUID()}.jpg")
                
                // Using continueWithTask is more robust for getting download URL immediately after upload
                val downloadUrl = imageRef.putFile(Uri.fromFile(compressed))
                    .continueWithTask { task ->
                        if (!task.isSuccessful) task.exception?.let { throw it }
                        imageRef.downloadUrl
                    }.await().toString()

                val productWithImage = product.copy(
                    id = UUID.randomUUID().toString(),
                    imageUrl = downloadUrl,
                    timestamp = System.currentTimeMillis()
                )
                db.collection(COLLECTION)
                    .document(productWithImage.id)
                    .set(productWithImage)
                    .await()

                localDb.productDao().insert(productWithImage)
                if (file.exists()) file.delete()
                
                Result.Success(productWithImage.id)
            } catch (e: Exception) {
                Result.Error(e.message ?: "Upload failed")
            }
        }

    private fun getFileFromUri(uri: Uri): File? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = File(context.cacheDir, "upload_${System.currentTimeMillis()}.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            file
        } catch (e: Exception) {
            null
        }
    }

    suspend fun uploadProductNoImage(product: Product): Result<String> =
        withContext(Dispatchers.IO) {
            try {
                val p = product.copy(
                    id = UUID.randomUUID().toString(),
                    timestamp = System.currentTimeMillis()
                )
                db.collection(COLLECTION).document(p.id).set(p).await()
                localDb.productDao().insert(p)
                Result.Success(p.id)
            } catch (e: Exception) {
                Result.Error(e.message ?: "Upload failed")
            }
        }

    suspend fun seedDemoData() = withContext(Dispatchers.IO) {
        val demo = listOf(
            Product("d1","Channapatna Toy","ಚನ್ನಪಟ್ಟಣ ಆಟಿಕೆ","चन्नपटना खिलौना","சன்னபட்டண பொம்மை",
                250.0,"Handmade lacquerware toy from Channapatna artisans","ಚನ್ನಪಟ್ಟಣದ ಕಲಾವಿದರಿಂದ ತಯಾರಿಸಲ್ಪಟ್ಟಿದೆ","TOYS",
                "","Raju Gowda","9876543210","Channapatna, Karnataka",0.0,0.0,50,true),
            Product("d2","Ilkal Saree","ಇಳಕಲ್ ಸೀರೆ","इलकल साड़ी","இல்கல் சேலை",
                1800.0,"Traditional handloom saree with Kasuti embroidery","ಸಾಂಪ್ರದಾಯಿಕ ಕೈಮಗ್ಗದ ಸೀರೆ","TEXTILES",
                "","Savitha Devi","9845012345","Ilkal, Bagalkot",0.0,0.0,12,true),
            Product("d3","Clay Pot Set","ಮಣ್ಣಿನ ಮಡಕೆ","मिट्टी के बर्तन","மண் பாத்திரம்",
                450.0,"Handcrafted terracotta pots – set of 3","ಕೈಯಿಂದ ಮಾಡಿದ ಮಣ್ಣಿನ ಪಾತ್ರೆಗಳು","POTTERY",
                "","Muniswamy","9900112233","Tumkur, Karnataka",0.0,0.0,25,true),
            Product("d4","Byadagi Chilli","ಬ್ಯಾಡಗಿ ಮೆಣಸಿನಕಾಯಿ","बयादगी मिर्च","பயாடகி மிளகாய்",
                120.0,"Premium Byadagi chilli – 500g pack. GI tagged spice.","ಉತ್ತಮ ಗುಣಮಟ್ಟದ ಬ್ಯಾಡಗಿ ಮೆಣಸಿನಕಾಯಿ","FOOD",
                "","Lakshmamma","9711223344","Byadagi, Haveri",0.0,0.0,200,true),
            Product("d5","Sandalwood Elephant","ಶ್ರೀಗಂಧದ ಆನೆ","चंदन हाथी","சந்தன யானை",
                3500.0,"Intricately carved sandalwood elephant figurine","ಶ್ರೀಗಂಧದ ಕೆತ್ತನೆಯ ಆನೆ","WOODCRAFT",
                "","Venkatesh Shilpi","9632587410","Mysuru, Karnataka",0.0,0.0,8,true),
            Product("d6","Navratna Necklace","ನವರತ್ನ ಹಾರ","नवरत्न नेकलेस","நவரத்ன நெಕ್ಲஸ்",
                2200.0,"Handcrafted Navratna necklace with semi-precious stones","ನವರತ್ನ ಹಾರ - ಹರಳುಗಳಿಂದ ಕೂಡಿದೆ","JEWELRY",
                "","Parvathi Jewels","8800990011","Hassan, Karnataka",0.0,0.0,15,true),
        )
        try {
            val batch = db.batch()
            demo.forEach { p -> batch.set(db.collection(COLLECTION).document(p.id), p) }
            batch.commit().await()
            localDb.productDao().insertAll(demo)
        } catch (_: Exception) {
            localDb.productDao().insertAll(demo)
        }
    }
}
