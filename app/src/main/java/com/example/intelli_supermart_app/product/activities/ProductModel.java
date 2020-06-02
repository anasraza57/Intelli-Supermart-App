package com.example.intelli_supermart_app.product.activities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private int id;
    private String product_name;
    private String product_price;
    private String product_quantity;
    private String slug;
    private String product_description;

    public ProductModel(int id, String product_name, String product_price, String product_quantity, String slug, String product_description) {
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.slug = slug;
        this.product_description = product_description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }


    static List<ProductModel> ParseJson(String json) {

        List<ProductModel> productModelList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray productsArray = jsonObject.getJSONArray("products");
            for (int i = 0; i < productsArray.length(); i++) {
                JSONObject productObject = productsArray.getJSONObject(i);
                int id = productObject.getInt("product_id");
                String desc = productObject.getString("product_description");
                String name = productObject.getString("product_name");
                String price = productObject.getString("product_price");
                String quantity = productObject.getString("product_quantity");
                String slug = productObject.getString("slug");
                ProductModel productModel = new ProductModel(id, name, price, quantity, slug, desc);
                productModelList.add(productModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return productModelList;
    }
}
