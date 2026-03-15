package com.example.q_less;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<FoodItem> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(FoodItem item) {
        // Create a copy with current selected quantity to avoid live updates to cart items from menu
        FoodItem cartItem;
        if (item.getImageRes() != 0) {
            cartItem = new FoodItem(item.getNameRes(), item.getName(), item.getPrice(), item.getImageRes());
        } else {
            cartItem = new FoodItem(item.getName(), item.getPrice(), item.getImageUrl());
        }
        cartItem.setQuantity(item.getQuantity());
        cartItems.add(cartItem);
    }

    public List<FoodItem> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        double total = 0;
        for (FoodItem item : cartItems) {
            total += (item.getPrice() * item.getQuantity());
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }
}