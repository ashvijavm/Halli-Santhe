package com.hallisanthe.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0014J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u0012H\u0002J\b\u0010$\u001a\u00020\u0012H\u0002J\b\u0010%\u001a\u00020\u0012H\u0002J\b\u0010&\u001a\u00020\u0012H\u0002J\b\u0010\'\u001a\u00020\u0012H\u0002J\b\u0010(\u001a\u00020\u0012H\u0002J\b\u0010)\u001a\u00020\u0012H\u0002J\b\u0010*\u001a\u00020\u0012H\u0002J\b\u0010+\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006,"}, d2 = {"Lcom/hallisanthe/ui/home/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/hallisanthe/adapter/ProductAdapter;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/hallisanthe/databinding/ActivityMainBinding;", "showingWishlist", "", "viewModel", "Lcom/hallisanthe/ui/home/HomeViewModel;", "getViewModel", "()Lcom/hallisanthe/ui/home/HomeViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "doLogout", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "openDetail", "product", "Lcom/hallisanthe/model/Product;", "setAppLocale", "lang", "", "setupBottomNavigation", "setupCategoryChips", "setupLanguageChips", "setupObservers", "setupRecyclerView", "setupSwipeRefresh", "showAccountOptions", "showLanguageDialog", "updateTitle", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.hallisanthe.databinding.ActivityMainBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.hallisanthe.adapter.ProductAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    private boolean showingWishlist = false;
    
    public MainActivity() {
        super();
    }
    
    private final com.hallisanthe.ui.home.HomeViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void updateTitle() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupLanguageChips() {
    }
    
    private final void setupCategoryChips() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupSwipeRefresh() {
    }
    
    private final void setupBottomNavigation() {
    }
    
    private final void showAccountOptions() {
    }
    
    private final void openDetail(com.hallisanthe.model.Product product) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void doLogout() {
    }
    
    private final void showLanguageDialog() {
    }
    
    private final void setAppLocale(java.lang.String lang) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
}