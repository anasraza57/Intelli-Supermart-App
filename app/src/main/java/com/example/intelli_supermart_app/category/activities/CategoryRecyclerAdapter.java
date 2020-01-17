package com.example.intelli_supermart_app.category.activities;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intelli_supermart_app.R;
import com.example.intelli_supermart_app.product.activities.ProductActivity;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {
    List<CategoryItem> categoryItems;
    Context context;
    int[] images;

    public CategoryRecyclerAdapter(List<CategoryItem> categoryItems, int[] gridImages, Context applicationContext) {
        this.categoryItems = categoryItems;
        this.images = gridImages;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryItem categoryItem = categoryItems.get(position);

        holder.imageView.setImageResource(categoryItem.getCategoryImage());
        holder.cat_title.setText(categoryItem.getText());
        holder.cat_description.setText(categoryItem.getSubtext());
        holder.upperlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ProductActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

//        CategoryGridAdaptor gridAdaptor = new CategoryGridAdaptor(images, context);
//        holder.gridLayout.setAdapter(gridAdaptor);
//
//        holder.gridLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, "Image: " + position, Toast.LENGTH_LONG).show();
//            }
//        });
//
        boolean isExpanded = categoryItem.isExpanded();
        if (isExpanded) {
//            holder.expandableLayout.setVisibility(View.VISIBLE);
            changeRotate(holder.button, 0f, 90f).start();
        } else {
//            holder.expandableLayout.setVisibility(View.GONE);
            changeRotate(holder.button, 90f, 0f).start();

        }
    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView cat_title, cat_description;
        RelativeLayout button;
        RelativeLayout upperlayout;
        GridView gridLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            cat_title = itemView.findViewById(R.id.category_name);
            cat_description = itemView.findViewById(R.id.category_description);
            button = itemView.findViewById(R.id.button);
//            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            upperlayout = itemView.findViewById(R.id.upperLayout);
//            gridLayout = itemView.findViewById(R.id.gridLayout);

            upperlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CategoryItem categoryItem = categoryItems.get(getAdapterPosition());
                    categoryItem.setExpanded(!categoryItem.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
