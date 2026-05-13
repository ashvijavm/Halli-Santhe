package com.hallisanthe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    val nameKn: String = "",   // Kannada
    val nameHi: String = "",   // Hindi
    val nameTa: String = "",   // Tamil
    val price: Double = 0.0,
    val description: String = "",
    val descriptionKn: String = "",
    val category: String = "",
    val imageUrl: String = "",
    val sellerName: String = "",
    val sellerPhone: String = "",
    val sellerLocation: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val stock: Int = 0,
    val isAvailable: Boolean = true,
    val timestamp: Long = System.currentTimeMillis(),
    val ownerUid: String = "", // UID of the user who uploaded it
    var isWishlisted: Boolean = false
)

enum class Category(val displayName: String, val emoji: String, val colorHex: String) {
    ALL("All", "🛒", "#3F51B5"),
    POTTERY("Pottery", "🏺", "#8D6E63"),
    TEXTILES("Textiles", "🧵", "#7B1FA2"),
    TOYS("Toys & Crafts", "🪆", "#F57F17"),
    JEWELRY("Jewelry", "💍", "#AD1457"),
    FOOD("Food & Spices", "🌶️", "#2E7D32"),
    WOODCRAFT("Woodcraft", "🪵", "#5D4037"),
    FLOWERS("Flowers", "🌸", "#C62828"),
    OTHER("Other", "✨", "#607D8B")
}
