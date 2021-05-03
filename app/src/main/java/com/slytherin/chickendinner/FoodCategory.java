package com.slytherin.chickendinner;

public class FoodCategory {
    private String Code;
    private String Name;
    private String Price;
    private String Status;

    public FoodCategory() {

    }

    public FoodCategory(String code, String name, String price, String status) {
        Code = code;
        Name = name;
        Price = price;
        Status = status;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
