
package com.darwinruiz.shoplite.models;

public class User {
    private final String email;
    private final String password;
    private final String role; // USER | ADMIN

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}





//ZZZ