
package com.darwinruiz.shoplite.repositories;

import com.darwinruiz.shoplite.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class UserRepository {
    private static final Map<String, User> USERS = new HashMap<>();

    static {
        USERS.put("admin@demo.com", new User("admin@demo.com", "admin123", "ADMIN"));
        USERS.put("user@demo.com", new User("user@demo.com", "user123", "USER"));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(USERS.get(email));
    }
}









//ZZZ