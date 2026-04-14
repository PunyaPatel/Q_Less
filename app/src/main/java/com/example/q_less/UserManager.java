package com.example.q_less;

public class UserManager {
    private static UserManager instance;
    private int id;
    private String name;
    private String email;

    private UserManager() {
        // Default values for prototype
        this.id = -1;
        this.name = "Indus Student";
        this.email = "student@indusuni.ac.in";
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void setUser(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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