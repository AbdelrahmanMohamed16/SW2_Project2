package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("name")
    private String name ;
    @JsonProperty("serialNumber")

    private String serialNumber ;
    @JsonProperty("vendor")

    private String vendor ;
    @JsonProperty("cat")

    private Category cat ;
    @JsonProperty("price")

    private double price ;
    @JsonProperty("quantity")

    public int quantity ;

    public Product(String name, String serialNumber, String vendor, Category cat, double price,int quantity) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.vendor = vendor;
        this.cat = cat;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        vendor = vendor;
    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
