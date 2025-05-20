package com.inventory.model;

public class Product {
    private int product_id;
    private String name;
    private double price;
    private int taxPercent;

    public Product() {}

    // Constructor with all fields
    public Product(int product_id, String name, double price, int taxPercent) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.taxPercent = taxPercent;
    }

    // Getters and Setters
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(int taxPercent) {
        this.taxPercent = taxPercent;
    }
}
