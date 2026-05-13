package com.hallisanthe.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0013\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\u0014"}, d2 = {"Lcom/hallisanthe/model/ProductDao;", "", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllProducts", "", "Lcom/hallisanthe/model/Product;", "getByCategory", "cat", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "product", "(Lcom/hallisanthe/model/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "products", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "q", "app_debug"})
@androidx.room.Dao()
public abstract interface ProductDao {
    
    @androidx.room.Query(value = "SELECT * FROM products ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllProducts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hallisanthe.model.Product>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE category = :cat ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String cat, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hallisanthe.model.Product>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE name LIKE \'%\' || :q || \'%\' OR nameKn LIKE \'%\' || :q || \'%\' OR nameHi LIKE \'%\' || :q || \'%\' ORDER BY timestamp DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object search(@org.jetbrains.annotations.NotNull()
    java.lang.String q, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hallisanthe.model.Product>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hallisanthe.model.Product> products, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.model.Product product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM products")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}