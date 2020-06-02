package com.example.intelli_supermart_app.category.activities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class CategoryModel {
    private int id;
    private String name;
    private String slug;

    private CategoryModel(int id, String name, String slug) {
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

    static List<CategoryModel> ParseJson(String json) {

        List<CategoryModel> categoryModelList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray categoriesArray = jsonObject.getJSONArray("categories");
            for (int i = 0; i < categoriesArray.length(); i++) {
                JSONObject categoryObject = categoriesArray.getJSONObject(i);
                int id = categoryObject.getInt("category_id");
                String name = categoryObject.getString("category_name");
                String slug = categoryObject.getString("slug");
                CategoryModel categoryModel = new CategoryModel(id, name, slug);
                categoryModelList.add(categoryModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categoryModelList;
    }
}
