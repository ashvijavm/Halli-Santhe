package com.hallisanthe.model

import android.content.Context
import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY timestamp DESC")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * FROM products WHERE category = :cat ORDER BY timestamp DESC")
    suspend fun getByCategory(cat: String): List<Product>

    @Query("SELECT * FROM products WHERE name LIKE '%' || :q || '%' OR nameKn LIKE '%' || :q || '%' OR nameHi LIKE '%' || :q || '%' ORDER BY timestamp DESC")
    suspend fun search(q: String): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Query("DELETE FROM products")
    suspend fun clearAll()
}

@Database(entities = [Product::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getInstance(ctx: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(ctx.applicationContext, AppDatabase::class.java, "hallisanthe_db")
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
    }
}
