package com.example.intelli_supermart_app.product.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intelli_supermart_app.R;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory3 extends Fragment {
    private RecyclerView recyclerView;
    private ProductRecyclerAdapter adapter;
    private List<Product> listProducts;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.category3, container, false);
        recyclerView = root.findViewById(R.id.recycler_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listProducts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listProducts.add(new Product("Product " + (i + 1), "Rs 100", "1 Piece", R.drawable.sharbat));
        }
        adapter = new ProductRecyclerAdapter(listProducts, getActivity());
        recyclerView.setAdapter(adapter);

        return root;
    }
}
