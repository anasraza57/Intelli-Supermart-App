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

public class ProductCategory1 extends Fragment {

    private RecyclerView recyclerView;
    private ProductRecyclerAdapter adapter;
    private List<ProductModel> listProducts;
//    int[] prodImages = {R.drawable.apple, R.drawable.guava, R.drawable.red_grapes, R.drawable.pomegranate,
//            R.drawable.peach, R.drawable.pineapple};
//    String[] prodTitles = {"Apple Juice", "Guava Juice", "Red Grapes Juice", "Pomegranate Juice",
//            "Peach Juice", "Pineapple Juice"};

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.category1, container, false);
        recyclerView = root.findViewById(R.id.recycler_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listProducts = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
//            listProducts.add(new ProductModel(prodTitles[i], "Rs 35", "1 Piece", prodImages[i]));
        }
        adapter = new ProductRecyclerAdapter(listProducts, getActivity());
        recyclerView.setAdapter(adapter);
        return root;
    }
}
