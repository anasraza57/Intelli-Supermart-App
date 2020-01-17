package com.example.intelli_supermart_app.cart.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intelli_supermart_app.R;

import java.util.List;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.CartViewHolder> implements View.OnClickListener {
    private List<Cart> listProducts;
    private Context context;

    public CartRecyclerAdapter(List<Cart> listProducts, Context context) {
        this.listProducts = listProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cart, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        final Cart product = listProducts.get(position);
        holder.product_name.setText(product.getProduct_name());
        holder.product_price.setText(product.getProduct_price());
        holder.product_description.setText(product.getProduct_description());
        holder.product_image.setImageResource(product.getProduct_image());
        holder.product_remove.setImageAlpha(product.getProduct_remove());
        holder.product_remove.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                Toast.makeText(context, "CategoryItem Removed", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name;
        public ImageView product_image;
        public TextView product_price;
        public TextView product_description;
        public ImageView product_remove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.item_name);
            product_image = itemView.findViewById(R.id.item_image);
            product_price = itemView.findViewById(R.id.item_price);
            product_description = itemView.findViewById(R.id.item_description);
            product_remove = itemView.findViewById(R.id.btn_cancel);
        }
    }
}
