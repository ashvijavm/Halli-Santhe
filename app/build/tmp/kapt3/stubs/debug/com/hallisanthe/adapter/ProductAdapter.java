package com.hallisanthe.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0011\u0012B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\u00062\n\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/hallisanthe/adapter/ProductAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/hallisanthe/model/Product;", "Lcom/hallisanthe/adapter/ProductAdapter$ProductViewHolder;", "onWishlistClick", "Lkotlin/Function1;", "", "onClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ProductViewHolder", "app_debug"})
public final class ProductAdapter extends androidx.recyclerview.widget.ListAdapter<com.hallisanthe.model.Product, com.hallisanthe.adapter.ProductAdapter.ProductViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.hallisanthe.model.Product, kotlin.Unit> onWishlistClick = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.hallisanthe.model.Product, kotlin.Unit> onClick = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.hallisanthe.model.Product> DIFF = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.hallisanthe.adapter.ProductAdapter.Companion Companion = null;
    
    public ProductAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hallisanthe.model.Product, kotlin.Unit> onWishlistClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hallisanthe.model.Product, kotlin.Unit> onClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.hallisanthe.adapter.ProductAdapter.ProductViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.adapter.ProductAdapter.ProductViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/hallisanthe/adapter/ProductAdapter$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/hallisanthe/model/Product;", "getDIFF", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.hallisanthe.model.Product> getDIFF() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/hallisanthe/adapter/ProductAdapter$ProductViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/hallisanthe/databinding/ItemProductBinding;", "(Lcom/hallisanthe/adapter/ProductAdapter;Lcom/hallisanthe/databinding/ItemProductBinding;)V", "bind", "", "product", "Lcom/hallisanthe/model/Product;", "app_debug"})
    public final class ProductViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.hallisanthe.databinding.ItemProductBinding binding = null;
        
        public ProductViewHolder(@org.jetbrains.annotations.NotNull()
        com.hallisanthe.databinding.ItemProductBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.hallisanthe.model.Product product) {
        }
    }
}