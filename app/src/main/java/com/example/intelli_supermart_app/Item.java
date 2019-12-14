package com.example.intelli_supermart_app;

public class Item {
    String text, subtext;
    Boolean expanded;

    public Item(String text, String subtext) {
        this.text = text;
        this.subtext = subtext;
        this.expanded = false;
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

