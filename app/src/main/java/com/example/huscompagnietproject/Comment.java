package com.example.huscompagnietproject;

public class Comment {

    private String email;
    private String userComment;
    private Products product;

    public Comment(String email, String userComment, Products product) {
        this.email = email;
        this.userComment = userComment;
        this.product = product;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
}
