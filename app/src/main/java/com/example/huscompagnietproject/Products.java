package com.example.huscompagnietproject;

import java.util.ArrayList;

public class Products {

    private String title;
    private String description;
    private double price;

    Products(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public double getPrice() { return price; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }





}
