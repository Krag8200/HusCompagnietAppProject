package com.example.huscompagnietproject;

public class Comment {

    private String email;
    private String userComment;
    private Product product;

    public Comment(String email, String userComment, Product product) {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
