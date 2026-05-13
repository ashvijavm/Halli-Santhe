package com.hallisanthe.repository;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J$\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u0013J\"\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\u0006\u0010\u0015\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u0013J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u0019H\u0002J\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0006\u0010\u0017\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u000e\u0010 \u001a\u00020!H\u0086@\u00a2\u0006\u0002\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/hallisanthe/repository/ProductRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "storage", "Lcom/google/firebase/storage/FirebaseStorage;", "localDb", "Lcom/hallisanthe/model/AppDatabase;", "COLLECTION", "", "getProducts", "Lcom/hallisanthe/repository/Result;", "", "Lcom/hallisanthe/model/Product;", "category", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchProducts", "query", "uploadProduct", "product", "imageUri", "Landroid/net/Uri;", "(Lcom/hallisanthe/model/Product;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFileFromUri", "Ljava/io/File;", "uri", "uploadProductNoImage", "(Lcom/hallisanthe/model/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seedDemoData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ProductRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
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
}