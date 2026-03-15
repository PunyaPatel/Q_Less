package com.example.q_less;

public class UserManager {
    private static UserManager instance;
    private String name;
    private String email;

    private UserManager() {
        // Default values for prototype
        this.name = "Indus Student";
        this.email = "student@indusuni.ac.in";
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void setUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}