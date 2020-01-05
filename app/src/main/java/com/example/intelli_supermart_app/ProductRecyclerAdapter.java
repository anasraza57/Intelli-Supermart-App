package com.example.intelli_supermart_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>
        implements View.OnClickListener {

    private List<ProductRecycler> listProducts;
    private Context context;

    public ProductRecyclerAdapter(List<ProductRecycler> listProducts, Context context) {
        this.listProducts = listProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product,parent,false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        final ProductRecycler product=listProducts.get(position);
        holder.product_name.setText(product.getProduct_name());
        holder.product_price.setText(product.getProduct_price());
        holder.product_quantity.setText(product.getProduct_quantity());
        holder.product_image.setImageAlpha(product.getProduct_image());
        holder.product_name.setOnClickListener(this);
        holder.product_image.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_image:
                Intent intent=new Intent(context,ProductActivity.class);
                context.startActivity(intent);
                break;
            case R.id.item_name:
                intent=new Intent(context,ProductActivity.class);
                context.startActivity(intent);
                break;
        }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView product_name;
        public ImageView product_image;
        public TextView product_price;
        public TextView product_quantity;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name=(TextView)itemView.findViewById(R.id.item_name);
            product_image=(ImageView)itemView.findViewById(R.id.item_image);
            product_price=(TextView)itemView.findViewById(R.id.item_price);
            product_quantity=(TextView)itemView.findViewById(R.id.item_quantity);
        }
    }
}