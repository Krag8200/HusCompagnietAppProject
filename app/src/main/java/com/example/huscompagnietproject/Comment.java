package com.example.huscompagnietproject;

public class Comment {

    private String email;
    private String userComment;

    public Comment(String email, String userComment) {
        this.email = email;
        this.userComment = userComment;
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

}
