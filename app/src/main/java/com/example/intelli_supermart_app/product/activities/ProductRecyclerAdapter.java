package com.example.intelli_supermart_app.product.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intelli_supermart_app.R;

import java.util.List;

class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>
        implements View.OnClickListener {

    private List<ProductModel> listProducts;
    private Context context;

    public ProductRecyclerAdapter(List<ProductModel> listProducts, Context context) {
        this.listProducts = listProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        final ProductModel product = listProducts.get(position);
        holder.product_name.setText(product.getProduct_name());
        holder.product_price.setText(product.getProduct_price());
        holder.product_quantity.setText(product.getProduct_quantity());
        holder.product_layout.setOnClickListener(this);
        holder.add_button.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.product_layout:
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.addBtn:
                Toast.makeText(context, "Category Added", Toast.LENGTH_LONG).show();
                break;
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout product_layout;
        Button add_button;
        TextView product_name;
        ImageView product_image;
        TextView product_price;
        TextView product_quantity;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.item_name);
            product_image = itemView.findViewById(R.id.item_image);
            product_price = itemView.findViewById(R.id.item_price);
            product_quantity = itemView.findViewById(R.id.item_quantity);
            product_layout = itemView.findViewById(R.id.product_layout);
            add_button = itemView.findViewById(R.id.addBtn);
        }
    }
}