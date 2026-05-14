package com.hallisanthe.ui.upload;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001dH\u0002J\n\u0010!\u001a\u0004\u0018\u00010\u001fH\u0002J\n\u0010\"\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0002J\u0012\u0010&\u001a\u00020\u001d2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\u0010\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020\u001dH\u0002J\u0018\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0002J\u0018\u00100\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0006H\u0002J\b\u00101\u001a\u00020\u001dH\u0002J\b\u00102\u001a\u00020\u001dH\u0002J\b\u00103\u001a\u00020\u001dH\u0002J\b\u00104\u001a\u00020\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\r\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/hallisanthe/ui/upload/UploadProductActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/hallisanthe/databinding/ActivityUploadProductBinding;", "capturedLat", "", "capturedLng", "locationManager", "Landroid/location/LocationManager;", "getLocationManager", "()Landroid/location/LocationManager;", "locationManager$delegate", "Lkotlin/Lazy;", "pickImage", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "repo", "Lcom/hallisanthe/repository/ProductRepository;", "getRepo", "()Lcom/hallisanthe/repository/ProductRepository;", "repo$delegate", "requestLocationPermission", "", "", "selectedImageUri", "Landroid/net/Uri;", "applyLocation", "", "location", "Landroid/location/Location;", "captureLocation", "getBestLastKnownLocation", "getEnabledLocationProvider", "hasLocationPermission", "", "isDeviceLocationEnabled", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "openGallery", "reverseGeocode", "latitude", "longitude", "reverseGeocodeFromNetwork", "setupCategoryDropdown", "setupLanguageToggle", "showEnableLocationDialog", "validateAndUpload", "app_debug"})
public final class UploadProductActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.hallisanthe.databinding.ActivityUploadProductBinding binding;
    @org.jetbrains.annotations.Nullable()
    private android.net.Uri selectedImageUri;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repo$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy locationManager$delegate = null;
    private double capturedLat = 0.0;
    private double capturedLng = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> requestLocationPermission = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> pickImage = null;
    
    public UploadProductActivity() {
        super();
    }
    
    private final com.hallisanthe.repository.ProductRepository getRepo() {
        return null;
    }
    
    private final android.location.LocationManager getLocationManager() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void captureLocation() {
    }
    
    private final boolean hasLocationPermission() {
        return false;
    }
    
    private final boolean isDeviceLocationEnabled() {
        return false;
    }
    
    private final void showEnableLocationDialog() {
    }
    
    private final android.location.Location getBestLastKnownLocation() {
        return null;
    }
    
    private final java.lang.String getEnabledLocationProvider() {
        return null;
    }
    
    private final void applyLocation(android.location.Location location) {
    }
    
    private final java.lang.String reverseGeocode(double latitude, double longitude) {
        return null;
    }
    
    private final java.lang.String reverseGeocodeFromNetwork(double latitude, double longitude) {
        return null;
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