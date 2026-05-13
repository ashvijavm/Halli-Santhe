package com.hallisanthe.ui.upload;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0015H\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0002J\b\u0010 \u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/hallisanthe/ui/upload/UploadProductActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/hallisanthe/databinding/ActivityUploadProductBinding;", "capturedLat", "", "capturedLng", "pickImage", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "repo", "Lcom/hallisanthe/repository/ProductRepository;", "getRepo", "()Lcom/hallisanthe/repository/ProductRepository;", "repo$delegate", "Lkotlin/Lazy;", "selectedImageUri", "Landroid/net/Uri;", "captureLocation", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "openGallery", "setupCategoryDropdown", "setupLanguageToggle", "validateAndUpload", "app_debug"})
public final class UploadProductActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.hallisanthe.databinding.ActivityUploadProductBinding binding;
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri selectedImageUri;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repo$delegate = null;
    private double capturedLat = 0.0;
    private double capturedLng = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> pickImage = null;
    
    public UploadProductActivity() {
        super();
    }
    
    private final com.hallisanthe.repository.ProductRepository getRepo() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void captureLocation() {
    }
    
    private final void setupLanguageToggle() {
    }
    
    private final void setupCategoryDropdown() {
    }
    
    private final void openGallery() {
    }
    
    private final void validateAndUpload() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
}