package com.hallisanthe.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0017J$\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u001cH\u0086@\u00a2\u0006\u0002\u0010\u0017J\"\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u00142\u0006\u0010\u001e\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u000e\u0010\u001f\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010\u0017J$\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u00142\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$H\u0086@\u00a2\u0006\u0002\u0010%J$\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u00142\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010\'\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010(J\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00060\u00142\u0006\u0010\"\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010*R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/hallisanthe/repository/ProductRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "COLLECTION", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "localDb", "Lcom/hallisanthe/model/AppDatabase;", "storage", "Lcom/google/firebase/storage/FirebaseStorage;", "getFileFromUri", "Ljava/io/File;", "uri", "Landroid/net/Uri;", "getMyProducts", "Lcom/hallisanthe/repository/Result;", "", "Lcom/hallisanthe/model/Product;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProducts", "category", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWishlistIds", "", "searchProducts", "query", "seedDemoData", "", "setWishlisted", "product", "wishlisted", "", "(Lcom/hallisanthe/model/Product;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadProduct", "imageUri", "(Lcom/hallisanthe/model/Product;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadProductNoImage", "(Lcom/hallisanthe/model/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ProductRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.storage.FirebaseStorage storage = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hallisanthe.model.AppDatabase localDb = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String COLLECTION = "products";
    
    public ProductRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getProducts(@org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hallisanthe.repository.Result<? extends java.util.List<com.hallisanthe.model.Product>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMyProducts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hallisanthe.repository.Result<? extends java.util.List<com.hallisanthe.model.Product>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchProducts(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hallisanthe.repository.Result<? extends java.util.List<com.hallisanthe.model.Product>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object uploadProduct(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.model.Product product, @org.jetbrains.annotations.NotNull()
    android.net.Uri imageUri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hallisanthe.repository.Result<java.lang.String>> $completion) {
        return null;
    }
    
    private final java.io.File getFileFromUri(android.net.Uri uri) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object uploadProductNoImage(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.model.Product product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hallisanthe.repository.Result<java.lang.String>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object seedDemoData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWishlistIds(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Set<java.lang.String>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setWishlisted(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.model.Product product, boolean wishlisted, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hallisanthe.repository.Result<kotlin.Unit>> $completion) {
        return null;
    }
}