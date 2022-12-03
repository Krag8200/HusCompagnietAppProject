package com.example.huscompagnietproject;

public class Product {

    private String title;
    private String description;
    private double price;
    private String category;
    private String enlistedByUser;
    private int productId;

    Product(String title, String description, double price, String category, String enlistedByUser, int productId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.enlistedByUser = enlistedByUser;
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public double getPrice() { return price; }

    public String getCategory() { return category; }

    public String getEnlistedByUser() { return enlistedByUser; }

    public int getProductId() { return productId; }

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

    public void setProductId(int productId) { this.productId = productId; }

}
