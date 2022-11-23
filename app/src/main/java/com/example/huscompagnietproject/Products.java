package com.example.huscompagnietproject;

import java.util.ArrayList;

public class Products {

    private String title;
    private int imageId;
    private String description;

    Products(String title, int imageId, String description) {
        this.title = title;
        this.imageId = imageId;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }

    public String getDescription() { return description; }

}
