package com.example.huscompagnietproject;

import java.util.ArrayList;

public class Products {

    private String title;
    private String description;
    private double price;
    private String category;

    Products(String title, String description, double price, String category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public double getPrice() { return price; }

    public String getCategory() { return category; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
