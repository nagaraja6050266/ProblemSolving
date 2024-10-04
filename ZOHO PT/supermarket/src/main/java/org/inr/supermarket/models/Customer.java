package org.inr.supermarket.models;

public class Customer {
    private int id;
    private String name;
    private String phNumber;

    public Customer() {
    }

    public Customer(int id, String name, String phNumber) {
        this.id = id;
        this.name = name;
        this.phNumber = phNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}
