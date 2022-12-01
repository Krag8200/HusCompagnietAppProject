package com.example.huscompagnietproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Products implements Serializable {

    private String title;
    private String description;
    private double price;
    private String category;
    private String enlistedByUser;

    Products(String title, String description, double price, String category, String enlistedByUser) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.enlistedByUser = enlistedByUser;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public double getPrice() { return price; }

    public String getCategory() { return category; }

    public String getEnlistedByUser() { return enlistedByUser; }

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

    public void setEnlistedByUser(String enlistedByUser) { this.enlistedByUser = enlistedByUser; }

}
