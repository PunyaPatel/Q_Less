package com.example.q_less;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String name;
    private int nameRes; // For localization
    private double price;
    private int imageRes;
    private String imageUrl;
    private int quantity = 1; // Default quantity

    public FoodItem(String name, double price, int imageRes) {
        this.name = name;
        this.price = price;
        this.imageRes = imageRes;
    }

    public FoodItem(int nameRes, String fallbackName, double price, int imageRes) {
        this.nameRes = nameRes;
        this.name = fallbackName;
        this.price = price;
        this.imageRes = imageRes;
    }

    public FoodItem(String name, double price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getNameRes() {
        return nameRes;
    }

    public double getPrice() {
        return price;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}