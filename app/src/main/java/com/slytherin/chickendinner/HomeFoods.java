package com.slytherin.chickendinner;

public class HomeFoods {
    private String Code;
    private String Image;
    private String Name;
    private String Price;
    private String Status;


    public HomeFoods() {

    }

    public HomeFoods(String code, String image, String name, String price, String status) {
        Code = code;
        Image = image;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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
