package com.example.intelli_supermart_app.cart.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intelli_supermart_app.R;
import com.example.intelli_supermart_app.product.activities.Product;

import java.util.List;

public class CartViewRecyclerAdapter extends RecyclerView.Adapter<CartViewRecyclerAdapter.CartViewHolder> {
    private List<Product> listProducts;
    private Context context;

    public CartViewRecyclerAdapter(List<Product> listProducts, Context context) {
        this.listProducts = listProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cart2, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        final Product product = listProducts.get(position);
        holder.product_name.setText(product.getProduct_name());
        holder.product_price.setText(product.getProduct_price());
        holder.product_quantity.setText(product.getProduct_quantity());
        holder.product_image.setImageResource(product.getProduct_image());
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        TextView product_name;
        ImageView product_image;
        TextView product_price;
        TextView product_quantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.item_name);
            product_image = itemView.findViewById(R.id.item_image);
            product_price = itemView.findViewById(R.id.item_price);
            product_quantity = itemView.findViewById(R.id.item_quantity);
        }
    }
}
