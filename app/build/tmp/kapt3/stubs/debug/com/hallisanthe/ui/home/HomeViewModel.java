package com.hallisanthe.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0010\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u0007J\u0006\u0010$\u001a\u00020\"J\u000e\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0007J\u0006\u0010\'\u001a\u00020(J\u000e\u0010)\u001a\u00020\"H\u0082@\u00a2\u0006\u0002\u0010*J\u000e\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\rR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/hallisanthe/ui/home/HomeViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_error", "Landroidx/lifecycle/MutableLiveData;", "", "_isEmpty", "", "_loading", "_products", "", "Lcom/hallisanthe/model/Product;", "_selectedCategory", "kotlin.jvm.PlatformType", "currentQuery", "error", "Landroidx/lifecycle/LiveData;", "getError", "()Landroidx/lifecycle/LiveData;", "isEmpty", "loading", "getLoading", "products", "getProducts", "repo", "Lcom/hallisanthe/repository/ProductRepository;", "selectedCategory", "getSelectedCategory", "wishlistIds", "", "applyWishlistState", "loadProducts", "", "category", "refresh", "search", "query", "seedDemo", "Lkotlinx/coroutines/Job;", "syncWishlistIds", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleWishlist", "product", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hallisanthe.repository.ProductRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.hallisanthe.model.Product>> _products = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.hallisanthe.model.Product>> products = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _loading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> loading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> error = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isEmpty = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isEmpty = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _selectedCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> selectedCategory = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentQuery = "";
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> wishlistIds = null;
    
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.hallisanthe.model.Product>> getProducts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isEmpty() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSelectedCategory() {
        return null;
    }
    
    public final void loadProducts(@org.jetbrains.annotations.NotNull()
    java.lang.String category) {
    }
    
    public final void toggleWishlist(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.model.Product product) {
    }
    
    public final void search(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job seedDemo() {
        return null;
    }
    
    public final void refresh() {
    }
    
    private final java.lang.Object syncWishlistIds(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.util.List<com.hallisanthe.model.Product> applyWishlistState(java.util.List<com.hallisanthe.model.Product> products) {
        return null;
    }
}