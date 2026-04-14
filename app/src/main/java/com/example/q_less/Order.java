package com.example.q_less;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int userId;
    private double totalPrice;
    private int status;
    private String timestamp;

    public Order(int id, int userId, double totalPrice, int status, String timestamp) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public double getTotalPrice() { return totalPrice; }
    public int getStatus() { return status; }
    public String getTimestamp() { return timestamp; }
    
    public String getStatusString() {
        return status == 0 ? "Under Preparation" : "Prepared";
    }
}