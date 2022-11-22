package com.example.huscompagnietproject;

import java.util.ArrayList;

public class Products {

    private String title;
    private int imageId;

    Products(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }

}
