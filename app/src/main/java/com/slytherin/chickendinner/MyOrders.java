package com.slytherin.chickendinner;

public class MyOrders {
    private String ofcode;
    private String ofqty;
    private String ofaddress;
    private String ofname;

    public MyOrders() {

    }

    public MyOrders(String ofcode, String ofqty, String ofaddress, String ofname) {
        this.ofcode = ofcode;
        this.ofqty = ofqty;
        this.ofaddress = ofaddress;
        this.ofname = ofname;
    }

    public String getOfcode() {
        return ofcode;
    }

    public void setOfcode(String ofcode) {
        this.ofcode = ofcode;
    }

    public String getOfqty() {
        return ofqty;
    }

    public void setOfqty(String ofqty) {
        this.ofqty = ofqty;
    }

    public String getOfaddress() {
        return ofaddress;
    }

    public void setOfaddress(String ofaddress) {
        this.ofaddress = ofaddress;
    }

    public String getOfname() {
        return ofname;
    }

    public void setOfname(String ofname) {
        this.ofname = ofname;
    }
}
