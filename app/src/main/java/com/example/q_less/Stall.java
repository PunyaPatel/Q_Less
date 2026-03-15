package com.example.q_less;

import java.io.Serializable;

public class Stall implements Serializable {
    private String name;
    private int logoRes;

    public Stall(String name, int logoRes) {
        this.name = name;
        this.logoRes = logoRes;
    }

    public String getName() {
        return name;
    }

    public int getLogoRes() {
        return logoRes;
    }
}