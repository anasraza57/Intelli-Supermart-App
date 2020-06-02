package com.example.intelli_supermart_app.category.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intelli_supermart_app.PictureModel;
import com.example.intelli_supermart_app.R;
import com.example.intelli_supermart_app.product.activities.ProductActivity;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {
    List<CategoryModel> category;
    List<PictureModel> categoryPictures;
    private Context context;

    CategoryRecyclerAdapter(List<CategoryModel> category, List<PictureModel> categoryPictures, Context applicationContext) {
        this.context = applicationContext;
        this.category = category;
        this.categoryPictures = categoryPictures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CategoryModel categoryModel = category.get(position);
        PictureModel pictureModel = categoryPictures.get(position);
        int id = context.getResources().getIdentifier(pictureModel.name.substring(0, pictureModel.name.indexOf(".")), "drawable", context.getPackageName());
        holder.imageView.setImageResource(id);
        holder.cat_title.setText(categoryModel.name);

        holder.categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("slug", categoryModel.slug);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, button;
        TextView cat_title;
        RelativeLayout categoryLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            cat_title = itemView.findViewById(R.id.category_name);
            button = itemView.findViewById(R.id.button);
            categoryLayout = itemView.findViewById(R.id.upperLayout);
        }
    }
}
