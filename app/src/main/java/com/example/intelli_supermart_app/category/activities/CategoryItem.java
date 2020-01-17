package com.example.intelli_supermart_app.category.activities;

public class CategoryItem {
    int categoryImage;
    String text, subtext;
    Boolean expanded;

    public CategoryItem(int categoryImage, String text, String subtext) {
        this.categoryImage = categoryImage;
        this.text = text;
        this.subtext = subtext;
        this.expanded = false;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public Boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }
}

