package com.hallisanthe.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hallisanthe.model.Product
import com.hallisanthe.repository.ProductRepository
import com.hallisanthe.repository.Result
import kotlinx.coroutines.launch

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = ProductRepository(app)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _selectedCategory = MutableLiveData("ALL")
    val selectedCategory: LiveData<String> = _selectedCategory

    private var currentQuery = ""
    private val wishlistIds = mutableSetOf<String>()

    init {
        loadProducts("ALL")
    }

    fun loadProducts(category: String = _selectedCategory.value ?: "ALL") {
        _selectedCategory.value = category
        currentQuery = ""
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            when (val result = repo.getProducts(category)) {
                is Result.Success -> {
                    syncWishlistIds()
                    _products.value = applyWishlistState(result.data)
                    _isEmpty.value = result.data.isEmpty()
                }
                is Result.Error -> {
                    _error.value = result.message
                    _isEmpty.value = true
                }
                else -> {}
            }
            _loading.value = false
        }
    }

    fun toggleWishlist(product: Product) {
        val currentList = _products.value?.toMutableList() ?: return
        val index = currentList.indexOfFirst { it.id == product.id }
        if (index != -1) {
            val shouldWishlist = !product.isWishlisted
            if (shouldWishlist) {
                wishlistIds.add(product.id)
            } else {
                wishlistIds.remove(product.id)
            }
            currentList[index] = currentList[index].copy(isWishlisted = shouldWishlist)
            _products.value = currentList

            viewModelScope.launch {
                when (val result = repo.setWishlisted(product, shouldWishlist)) {
                    is Result.Error -> {
                        _error.value = result.message
                        if (shouldWishlist) wishlistIds.remove(product.id) else wishlistIds.add(product.id)
                        _products.value = currentList.toMutableList().also {
                            it[index] = it[index].copy(isWishlisted = product.isWishlisted)
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    fun search(query: String) {
        currentQuery = query
        if (query.isBlank()) { loadProducts(); return }
        viewModelScope.launch {
            _loading.value = true
            when (val result = repo.searchProducts(query)) {
                is Result.Success -> {
                    syncWishlistIds()
                    _products.value = applyWishlistState(result.data)
                    _isEmpty.value = result.data.isEmpty()
                }
                is Result.Error -> _error.value = result.message
                else -> {}
            }
            _loading.value = false
        }
    }

    fun seedDemo() = viewModelScope.launch {
        repo.seedDemoData()
        loadProducts()
    }

    fun refresh() = loadProducts(_selectedCategory.value ?: "ALL")

    private suspend fun syncWishlistIds() {
        wishlistIds.clear()
        wishlistIds.addAll(repo.getWishlistIds())
    }

    private fun applyWishlistState(products: List<Product>): List<Product> {
        return products.map { product ->
            product.copy(isWishlisted = product.isWishlisted || wishlistIds.contains(product.id))
        }
    }
}
