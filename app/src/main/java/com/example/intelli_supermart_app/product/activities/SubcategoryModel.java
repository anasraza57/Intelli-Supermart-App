package com.example.intelli_supermart_app.product.activities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryModel {
    int id;
    String name;
    String slug;

    private SubcategoryModel(int id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    static List<SubcategoryModel> ParseJson(String json) {

        List<SubcategoryModel> subcategoryModelList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray subcategoriesArray = jsonObject.getJSONArray("subcategories");
            for (int i = 0; i < subcategoriesArray.length(); i++) {
                JSONObject subcategoryObject = subcategoriesArray.getJSONObject(i);
                int id = subcategoryObject.getInt("subcategory_id");
                String name = subcategoryObject.getString("subcategory_name");
                String slug = subcategoryObject.getString("slug");
                SubcategoryModel subcategoryModel = new SubcategoryModel(id, name, slug);
                subcategoryModelList.add(subcategoryModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return subcategoryModelList;
    }
}
