package com.restaurant.restaurant.model.Enums;

public enum Roles {
    ADMIN("ADMIN"), USER("USER");
    private String role;

    Roles(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return this.role;
    }
}
