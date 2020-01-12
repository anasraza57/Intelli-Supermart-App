package com.example.intelli_supermart_app.cart.activities;

class Cart {
    private String product_name;
    private String product_price;
    private String product_description;
    private int product_image;
    private int product_remove;

    Cart(String product_name, String product_price, String product_description, int product_image, int product_remove) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_description = product_description;
        this.product_image = product_image;
        this.product_remove = product_remove;
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

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getProduct_image() {
        return product_image;
    }

    public void setProduct_image(int product_image) {
        this.product_image = product_image;
    }

    public int getProduct_remove() {
        return product_remove;
    }

    public void setProduct_remove(int product_remove) {
        this.product_remove = product_remove;
    }
}
